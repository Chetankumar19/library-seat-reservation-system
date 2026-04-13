package com.libraryseatbooking.Mapper;

import com.libraryseatbooking.dto.SeatDTO;
import com.libraryseatbooking.model.SeatEntity;
import org.springframework.stereotype.Component;

@Component
public class SeatMapper {

    public SeatDTO toDto(SeatEntity entity) {
        return SeatDTO.builder()
                .seatId(entity.getSeatId())
                .seatNumber(entity.getSeatNumber())
                .row(entity.getRow())
                .column(entity.getColumn())
                .build();
    }
}