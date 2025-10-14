package com.example.tripapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "trips")
@Data // Generates getters, setters, toString, equals, and hashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Trip {

    public enum Status {
        PLANNED,
        ONGOING,
        COMPLETED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Primary Key, Auto-generated

    @Column(nullable = false)
    private String destination; // Cannot be empty

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate; // Cannot be null

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate; // Must be after startDate

    @Column(nullable = false)
    private Double price; // Must be positive

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;
}