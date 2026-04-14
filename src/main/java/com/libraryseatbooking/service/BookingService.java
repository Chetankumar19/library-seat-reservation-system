package com.libraryseatbooking.service;

import com.libraryseatbooking.Mapper.BookingMapper;
import com.libraryseatbooking.dto.BookingRequestDTO;
import com.libraryseatbooking.dto.BookingResponseDTO;
import com.libraryseatbooking.dto.SeatAvailabilityDTO;
import com.libraryseatbooking.dto.SeatAvailabilityResponseDTO;
import com.libraryseatbooking.enums.PaymentStatus;
import com.libraryseatbooking.model.BookingEntity;
import com.libraryseatbooking.model.SeatEntity;

import com.libraryseatbooking.repo.BookingRepository;
import com.libraryseatbooking.repo.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final SeatRepository seatRepository;
    private final BookingMapper bookingMapper;

    // ── Create booking ───────────────────────────────────────────

    public BookingResponseDTO createBooking(BookingRequestDTO request) {

        // 1. find the seat
        SeatEntity seat = seatRepository.findBySeatId(request.getSeatId().toUpperCase())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Seat not found: " + request.getSeatId()));

        // 2. calculate end date
        LocalDate endDate = request.getStartDate()
                .plusMonths(request.getDurationMonths());

        // 3. check if seat is already booked in this range
        boolean alreadyBooked = bookingRepository.existsOverlappingBooking(
                seat.getId(),
                request.getStartDate(),
                endDate
        );

        if (alreadyBooked) {
            throw new IllegalArgumentException(
                    "Seat " + request.getSeatId() + " is already booked for the selected period.");
        }

        // 4. create booking with PENDING payment
        BookingEntity booking = BookingEntity.builder()
                .seat(seat)
                .userName(request.getUserName())
                .phoneNumber(request.getPhoneNumber())
                .startDate(request.getStartDate())
                .endDate(endDate)
                .durationMonths(request.getDurationMonths())
                .bookedAt(LocalDateTime.now())
                .paymentStatus(PaymentStatus.PENDING)
                .build();

        return bookingMapper.toDto(bookingRepository.save(booking));
    }

    // ── Get booking by id ────────────────────────────────────────

    public BookingResponseDTO getBookingById(Long id) {
        return bookingRepository.findById(id)
                .map(bookingMapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Booking not found with id: " + id));
    }

    // ── Get bookings by phone number ─────────────────────────────

    public List<BookingResponseDTO> getBookingsByPhone(String phoneNumber) {
        return bookingRepository.findByPhoneNumberOrderByStartDateDesc(phoneNumber)
                .stream()
                .map(bookingMapper::toDto)
                .toList();
    }
    public SeatAvailabilityResponseDTO getSeatAvailability(
            LocalDate startDate,
            int durationMonths
    ) {
        LocalDate endDate = startDate.plusMonths(durationMonths);

        // 1. get all seats
        List<SeatEntity> seats = seatRepository.findAll();

        // 2. get booked seat ids in this time range
        List<Long> bookedSeatIds = bookingRepository.findBookedSeatIds(startDate, endDate);

        // 3. map to DTO
        List<SeatAvailabilityDTO> seatDTOs = seats.stream()
                .map(seat -> {
                    boolean isAvailable = !bookedSeatIds.contains(seat.getId());

                    return SeatAvailabilityDTO.builder()
                            .seatId(seat.getSeatId())
                            .seatNumber(seat.getSeatNumber())
                            .row(seat.getRow())
                            .column(seat.getColumn())
                            .isAvailable(isAvailable)
                            .build();
                })
                .toList();

        int totalSeats = seats.size();
        int totalBooked = bookedSeatIds.size();
        int totalAvailable = totalSeats - totalBooked;

        return SeatAvailabilityResponseDTO.builder()
                .totalSeats(totalSeats)
                .totalBooked(totalBooked)
                .totalAvailable(totalAvailable)
                .seats(seatDTOs)
                .build();
    }

}