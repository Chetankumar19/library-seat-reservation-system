package com.libraryseatbooking.controller;

import com.libraryseatbooking.dto.BookingRequestDTO;
import com.libraryseatbooking.dto.BookingResponseDTO;
import com.libraryseatbooking.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    // POST /api/bookings
    @PostMapping()
    public ResponseEntity<BookingResponseDTO> createBooking(
            @Valid @RequestBody BookingRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookingService.createBooking(request));
    }

    // GET /api/bookings/phone/9876543210
    @GetMapping("/phone/{phoneNumber}")
    public ResponseEntity<List<BookingResponseDTO>> getBookingsByPhone(
            @PathVariable String phoneNumber) {
        return ResponseEntity.ok(bookingService.getBookingsByPhone(phoneNumber));
    }

}