package com.libraryseatbooking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.libraryseatbooking.enums.PaymentStatus;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponseDTO {

    private Long id;

    @JsonProperty("seat_id")
    private String seatId;              // "A1"

    @JsonProperty("seat_number")
    private String seatNumber;          // "1A"

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("start_date")
    private LocalDate startDate;

    @JsonProperty("end_date")
    private LocalDate endDate;

    @JsonProperty("duration_months")
    private int durationMonths;

    @JsonProperty("booked_at")
    private LocalDateTime bookedAt;

    @JsonProperty("payment_status")
    private PaymentStatus paymentStatus;
}