package com.libraryseatbooking.Mapper;


import com.libraryseatbooking.dto.BookingResponseDTO;
import com.libraryseatbooking.model.BookingEntity;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {

    public BookingResponseDTO toDto(BookingEntity entity) {
        return BookingResponseDTO.builder()
                .id(entity.getId())
                .seatId(entity.getSeat().getSeatId())
                .seatNumber(entity.getSeat().getSeatNumber())
                .userName(entity.getUserName())
                .phoneNumber(entity.getPhoneNumber())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .durationMonths(entity.getDurationMonths())
                .bookedAt(entity.getBookedAt())
                .paymentStatus(entity.getPaymentStatus())
                .build();
    }
}