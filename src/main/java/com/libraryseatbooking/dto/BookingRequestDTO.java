package com.libraryseatbooking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequestDTO {

    @NotBlank(message = "Seat ID is required")
    @JsonProperty("seat_id")
    private String seatId;

    @NotBlank(message = "Name is required")
    @JsonProperty("user_name")
    private String userName;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    @JsonProperty("phone_number")
    private String phoneNumber;

    @NotNull(message = "Start date is required")
    @FutureOrPresent(message = "Start date cannot be in the past")
    @JsonProperty("start_date")
    private LocalDate startDate;

    @Min(value = 1, message = "Duration must be at least 1 month")
    @Max(value = 12, message = "Duration cannot exceed 12 months")
    @JsonProperty("duration_months")
    private int durationMonths = 1;
}