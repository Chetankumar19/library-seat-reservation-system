package com.libraryseatbooking.controller;

import com.libraryseatbooking.dto.SeatDTO;
import com.libraryseatbooking.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seats")
@RequiredArgsConstructor
public class SeatController {

    private final SeatService seatService;

    // GET /api/seats
    @GetMapping
    public ResponseEntity<List<SeatDTO>> getAllSeats() {
        return ResponseEntity.ok(seatService.getAllSeats());
    }

    // GET /api/seats/row/A
    @GetMapping("/row/{row}")
    public ResponseEntity<List<SeatDTO>> getSeatsByRow(@PathVariable String row) {
        return ResponseEntity.ok(seatService.getSeatsByRow(row));
    }

    // GET /api/seats/id/A1
    @GetMapping("/id/{seatId}")
    public ResponseEntity<SeatDTO> getSeatById(@PathVariable String seatId) {
        return ResponseEntity.ok(seatService.getSeatById(seatId));
    }

    // GET /api/seats/number/1A
    @GetMapping("/number/{seatNumber}")
    public ResponseEntity<SeatDTO> getSeatByNumber(@PathVariable String seatNumber) {
        return ResponseEntity.ok(seatService.getSeatByNumber(seatNumber));
    }
}