package com.example.airbnb.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "guest_id", nullable = false)
    private User guest; // Кто бронирует

    @ManyToOne
    @JoinColumn(name = "property_id", nullable = false)
    private Property property; // Что бронируют

    @Column(nullable = false)
    private LocalDate checkInDate; // Дата заезда

    @Column(nullable = false)
    private LocalDate checkOutDate; // Дата выезда

    @Column(nullable = false)
    private BigDecimal totalPrice;
}