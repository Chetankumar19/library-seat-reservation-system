package com.libraryseatbooking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatAvailabilityResponseDTO {

    @JsonProperty("total_seats")
    private int totalSeats;

    @JsonProperty("total_booked")
    private int totalBooked;

    @JsonProperty("total_available")
    private int totalAvailable;

    @JsonProperty("seats")
    private List<SeatAvailabilityDTO> seats;
}