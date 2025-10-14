package com.example.tripapp.dto;

import com.example.tripapp.entity.Trip;
import lombok.Data;
import java.time.LocalDate;

@Data
public class TripResponse {

    private Integer id;
    private String destination;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double price;
    private Trip.Status status;

    // Helper method to map the Entity to the DTO
    public static TripResponse fromEntity(Trip trip) {
        TripResponse response = new TripResponse();
        response.setId(trip.getId());
        response.setDestination(trip.getDestination());
        response.setStartDate(trip.getStartDate());
        response.setEndDate(trip.getEndDate());
        response.setPrice(trip.getPrice());
        response.setStatus(trip.getStatus());
        return response;
    }
}
