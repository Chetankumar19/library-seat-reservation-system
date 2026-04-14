package com.libraryseatbooking.model;

import com.libraryseatbooking.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "bookings",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"seat_id", "start_date", "end_date"})
        }
)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_id", nullable = false)
    private SeatEntity seat;            // which seat

    @Column(name = "user_name", nullable = false, length = 100)
    private String userName;            // "John Doe"

    @Column(name = "phone_number", nullable = false, length = 15)
    private String phoneNumber;         // "9876543210"

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;        // 2026-04-01

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;          // calculated → startDate + durationMonths

    @Column(name = "duration_months", nullable = false)
    private int durationMonths;         // 1, 2, 3...

    @Column(name = "booked_at", nullable = false)
    private LocalDateTime bookedAt;     // when booking was created

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false, length = 20)
    private PaymentStatus paymentStatus; // PENDING | PAID | FAILED
}