package com.libraryseatbooking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatDTO {

    @JsonProperty("seat_id")
    private String seatId;         // "A1"

    @JsonProperty("seat_number")
    private String seatNumber;     // "1A"

    private String row;            // "A"

    private int column;            // 1
}