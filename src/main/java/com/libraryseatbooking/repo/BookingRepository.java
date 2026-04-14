package com.libraryseatbooking.repo;


import com.libraryseatbooking.model.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Long> {

    // check if seat is already booked within the requested date range
    @Query("""
        SELECT COUNT(b) > 0 FROM BookingEntity b
        WHERE b.seat.id = :seatId
        AND b.startDate < :endDate
        AND b.endDate > :startDate
        AND b.paymentStatus <> 'FAILED'
    """)
    boolean existsOverlappingBooking(
            @Param("seatId")    Long seatId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate")   LocalDate endDate
    );

    @Query("""
    SELECT b.seat.id
    FROM BookingEntity b
    WHERE b.startDate < :endDate
      AND b.endDate > :startDate
""")
    List<Long> findBookedSeatIds(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    // get all bookings by phone number (user's booking history)
    List<BookingEntity> findByPhoneNumberOrderByStartDateDesc(String phoneNumber);

    // get all bookings within a date range (admin use)
    @Query("""
        SELECT b FROM BookingEntity b
        WHERE b.startDate >= :startDate
        AND b.endDate <= :endDate
        ORDER BY b.startDate ASC
    """)
    List<BookingEntity> findBookingsWithinRange(
            @Param("startDate") LocalDate startDate,
            @Param("endDate")   LocalDate endDate
    );
}