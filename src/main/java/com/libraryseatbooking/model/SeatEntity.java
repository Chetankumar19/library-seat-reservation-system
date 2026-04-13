package com.libraryseatbooking.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "seats")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "seat_id", nullable = false, unique = true, length = 5)
    private String seatId;          // internal key e.g. "A1"

    @Column(name = "seat_number", nullable = false, unique = true, length = 5)
    private String seatNumber;      // display label e.g. "1A"

    @Column(name = "row_label", nullable = false, length = 2)
    private String row;             // "A", "B" ... "H"

    @Column(name = "col_number", nullable = false)
    private int column;             // 1 .. 10


}