package com.example.tripapp.service.impl;

import com.example.tripapp.dto.TripRequest;
import com.example.tripapp.dto.TripSummaryResponse;
import com.example.tripapp.entity.Trip;
import com.example.tripapp.exception.GlobalExceptionHandler;
import com.example.tripapp.exception.ResourceNotFoundException;
import com.example.tripapp.repository.TripRepository;
import com.example.tripapp.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class TripServiceImpl implements TripService {

    @Autowired
    private TripRepository tripRepository;

    @Override
    public Trip createTrip(Trip trip) {
        validateTripDates(trip.getStartDate(), trip.getEndDate());
        return tripRepository.save(trip);
    }

    @Override
    public Page<Trip> getAllTrips(Pageable pageable) {
        return tripRepository.findAll(pageable);
    }

    @Override
    public Trip getTripById(Integer id) {
        return tripRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trip", "id", id));
    }

    @Override
    public Trip updateTrip(Integer id, Trip tripDetails) {
        Trip trip = tripRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trip", "id", id));

        validateTripDates(tripDetails.getStartDate(), tripDetails.getEndDate());

        trip.setDestination(tripDetails.getDestination());
        trip.setStartDate(tripDetails.getStartDate());
        trip.setEndDate(tripDetails.getEndDate());
        trip.setPrice(tripDetails.getPrice());
        trip.setStatus(tripDetails.getStatus());

        return tripRepository.save(trip);
    }

    @Override
    public void deleteTrip(Integer id) {
        Trip trip = tripRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trip", "id", id));
        tripRepository.delete(trip);
    }
    
    // ----------------------------------------------------
    // Additional Features
    // ----------------------------------------------------

    @Override
    public List<Trip> searchTripsByDestination(String destination) {
        return tripRepository.findByDestinationContainingIgnoreCase(destination);
    }

    @Override
    public List<Trip> filterTripsByStatus(Trip.Status status) {
        return tripRepository.findByStatus(status);
    }
    
    @Override
    public List<Trip> getTripsBetweenDates(LocalDate start, LocalDate end) {
        return tripRepository.findTripsBetweenDates(start, end);
    }

    @Override
    public TripSummaryResponse getTripSummary() {
        Map<String, Object> summary = tripRepository.getTripSummary();

        // Ensure we handle null/empty results safely
        if (summary == null || summary.get("totalTrips") == null || (Long) summary.get("totalTrips") == 0) {
             return TripSummaryResponse.builder()
                    .totalTrips(0L)
                    .minPrice(0.0)
                    .maxPrice(0.0)
                    .averagePrice(0.0)
                    .build();
        }
        
        // The results from the native query are typically BigDecimals or Doubles for aggregates, 
        // and Long for COUNT. We cast them accordingly.
        return TripSummaryResponse.builder()
                .totalTrips(((Number) summary.get("totalTrips")).longValue())
                .minPrice(((Number) summary.get("minPrice")).doubleValue())
                .maxPrice(((Number) summary.get("maxPrice")).doubleValue())
                .averagePrice(((Number) summary.get("averagePrice")).doubleValue())
                .build();
    }
    
    // ----------------------------------------------------
    // Helper Methods
    // ----------------------------------------------------
    
    @Override
    public Trip convertToEntity(TripRequest dto) {
        return Trip.builder()
                .destination(dto.getDestination())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .price(dto.getPrice())
                .status(dto.getStatus())
                .build();
    }
    
    /**
     * Custom validation logic: Ensure endDate is not before startDate.
     */
    private void validateTripDates(LocalDate startDate, LocalDate endDate) {
        if (endDate.isBefore(startDate)) {
            throw new GlobalExceptionHandler.DateValidationException(
                "End date (" + endDate + ") must be after or equal to the start date (" + startDate + ")."
            );
        }
    }
}