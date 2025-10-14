package com.example.tripapp.service;

import com.example.tripapp.dto.TripRequest;
import com.example.tripapp.dto.TripSummaryResponse;
import com.example.tripapp.entity.Trip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface TripService {
    Trip createTrip(Trip trip);
    Page<Trip> getAllTrips(Pageable pageable);
    Trip getTripById(Integer id);
    Trip updateTrip(Integer id, Trip tripDetails);
    void deleteTrip(Integer id);
    
    // Conversion Helper
    Trip convertToEntity(TripRequest dto);

    // Additional Features
    List<Trip> searchTripsByDestination(String destination);
    List<Trip> filterTripsByStatus(Trip.Status status);
    List<Trip> getTripsBetweenDates(LocalDate start, LocalDate end);
    TripSummaryResponse getTripSummary();
}