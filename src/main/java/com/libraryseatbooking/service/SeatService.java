package com.libraryseatbooking.service;

import com.libraryseatbooking.Mapper.SeatMapper;
import com.libraryseatbooking.dto.SeatDTO;
import com.libraryseatbooking.repo.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatService {

    private final SeatRepository seatRepository;
    private final SeatMapper seatMapper;

    // ── Get all seats ────────────────────────────────────────────

    public List<SeatDTO> getAllSeats() {
        return seatRepository.findAll()
                .stream()
                .map(seatMapper::toDto)
                .toList();
    }

    // ── Get by row ───────────────────────────────────────────────

    public List<SeatDTO> getSeatsByRow(String row) {
        return seatRepository.findByRowOrderByColumnAsc(row.toUpperCase())
                .stream()
                .map(seatMapper::toDto)
                .toList();
    }

    // ── Get by seat_id ("A1") ────────────────────────────────────

    public SeatDTO getSeatById(String seatId) {
        return seatRepository.findBySeatId(seatId.toUpperCase())
                .map(seatMapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Seat not found with seat_id: " + seatId));
    }

    // ── Get by seat_number ("1A") ────────────────────────────────

    public SeatDTO getSeatByNumber(String seatNumber) {
        return seatRepository.findBySeatNumber(seatNumber.toUpperCase())
                .map(seatMapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Seat not found with seat_number: " + seatNumber));
    }
}