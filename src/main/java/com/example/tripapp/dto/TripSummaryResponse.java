package com.example.tripapp.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TripSummaryResponse {
    private Long totalTrips;
    private Double minPrice;
    private Double maxPrice;
    private Double averagePrice;
}
