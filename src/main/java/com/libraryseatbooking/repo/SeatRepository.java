package com.libraryseatbooking.repo;

import com.libraryseatbooking.model.SeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<SeatEntity, Long> {

    Optional<SeatEntity> findBySeatId(String seatId);

    Optional<SeatEntity> findBySeatNumber(String seatNumber);

    List<SeatEntity> findByRowOrderByColumnAsc(String row);
}