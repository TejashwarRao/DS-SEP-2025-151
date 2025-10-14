package com.example.tripapp.dto;

import com.example.tripapp.entity.Trip;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data // Includes @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor
public class TripRequest {

    @NotBlank(message = "Destination cannot be empty")
    private String destination;

    @NotNull(message = "Start date is required")
    @FutureOrPresent(message = "Start date must be today or in the future")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    private LocalDate endDate;

    @NotNull(message = "Price is required")
    @Min(value = 1, message = "Price must be greater than zero")
    private Double price;

    @NotNull(message = "Status is required (PLANNED, ONGOING, COMPLETED)")
    private Trip.Status status;
}