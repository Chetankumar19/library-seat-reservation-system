package com.libraryseatbooking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatAvailabilityDTO {

    @JsonProperty("seat_id")
    private String seatId;

    @JsonProperty("seat_number")
    private String seatNumber;

    private String row;

    private int column;

    @JsonProperty("is_available")
    private boolean isAvailable;
}