package com.libraryseatbooking.service;

import com.libraryseatbooking.Mapper.SeatMapper;
import com.libraryseatbooking.dto.SeatAvailabilityDTO;
import com.libraryseatbooking.dto.SeatAvailabilityResponseDTO;
import com.libraryseatbooking.dto.SeatDTO;
import com.libraryseatbooking.model.SeatEntity;
import com.libraryseatbooking.repo.BookingRepository;
import com.libraryseatbooking.repo.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatService {

    private final SeatRepository seatRepository;
    private final SeatMapper seatMapper;
    private final BookingRepository bookingRepository;


}