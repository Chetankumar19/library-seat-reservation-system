package com.libraryseatbooking.controller;

import com.libraryseatbooking.dto.SeatAvailabilityResponseDTO;
import com.libraryseatbooking.dto.SeatDTO;
import com.libraryseatbooking.service.BookingService;
import com.libraryseatbooking.service.SeatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/seats")
@RequiredArgsConstructor
public class SeatController {


    private final BookingService bookingService;

    @GetMapping("/availability")
    public ResponseEntity<SeatAvailabilityResponseDTO> getSeatAvailability(
            @RequestParam LocalDate startDate,
            @RequestParam int durationMonths
    ) {
        log.info("Fetching seat availability for startDate={} durationMonths={}",
                startDate, durationMonths);
        return ResponseEntity.ok(
                bookingService.getSeatAvailability(startDate, durationMonths)
        );
    }
}