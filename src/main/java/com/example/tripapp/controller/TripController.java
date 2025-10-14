package com.example.tripapp.controller;

import com.example.tripapp.dto.TripRequest;
import com.example.tripapp.dto.TripResponse;
import com.example.tripapp.dto.TripSummaryResponse; // <-- Crucial DTO import
import com.example.tripapp.entity.Trip;
import com.example.tripapp.service.TripService;
import io.swagger.v3.oas.annotations.Operation; // <-- Swagger import
import io.swagger.v3.oas.annotations.tags.Tag;     // <-- Swagger import
import jakarta.validation.Valid;                  // <-- Validation import
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/trips")
@Tag(name = "Trip Management", description = "CRUD, Search, Filter, and Summary operations for Trips.")
public class TripController {

    @Autowired
    private TripService tripService;

    // 1. Create Trip (POST /api/trips)
    @PostMapping
    @Operation(summary = "Add a new trip")
    public ResponseEntity<TripResponse> createTrip(@Valid @RequestBody TripRequest tripRequest) {
        Trip trip = tripService.convertToEntity(tripRequest);
        Trip createdTrip = tripService.createTrip(trip);
        return new ResponseEntity<>(TripResponse.fromEntity(createdTrip), HttpStatus.CREATED);
    }

    // 2. Get All Trips with Pagination & Sorting (GET /api/trips)
    // Example: /api/trips?page=0&size=5&sort=startDate,asc
    @GetMapping
    @Operation(summary = "Get a paginated and sorted list of all trips")
    public ResponseEntity<Page<TripResponse>> getAllTrips(Pageable pageable) {
        Page<Trip> tripPage = tripService.getAllTrips(pageable);
        Page<TripResponse> responsePage = tripPage.map(TripResponse::fromEntity);
        return ResponseEntity.ok(responsePage);
    }

    // 3. Get Trip by ID (GET /api/trips/{id})
    @GetMapping("/{id}")
    @Operation(summary = "Get a trip by ID")
    public ResponseEntity<TripResponse> getTripById(@PathVariable Integer id) {
        Trip trip = tripService.getTripById(id);
        return ResponseEntity.ok(TripResponse.fromEntity(trip));
    }

    // 4. Update Trip by ID (PUT /api/trips/{id})
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing trip by its ID")
    public ResponseEntity<TripResponse> updateTrip(@PathVariable Integer id, @Valid @RequestBody TripRequest tripRequest) {
        Trip tripDetails = tripService.convertToEntity(tripRequest);
        Trip updatedTrip = tripService.updateTrip(id, tripDetails);
        return ResponseEntity.ok(TripResponse.fromEntity(updatedTrip));
    }

    // 5. Delete Trip by ID (DELETE /api/trips/{id})
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a trip by ID")
    public ResponseEntity<Void> deleteTrip(@PathVariable Integer id) {
        tripService.deleteTrip(id);
        return ResponseEntity.noContent().build();
    }

    // 6. Search Trips by Destination (GET /api/trips/search?destination=...)
    @GetMapping("/search")
    @Operation(summary = "Search trips by destination (partial or full match)")
    public ResponseEntity<List<TripResponse>> searchTripsByDestination(@RequestParam String destination) {
        List<Trip> trips = tripService.searchTripsByDestination(destination);
        List<TripResponse> response = trips.stream()
                .map(TripResponse::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    // 7. Filter Trips by Status (GET /api/trips/filter?status=...)
    @GetMapping("/filter")
    @Operation(summary = "Filter trips by status (PLANNED, ONGOING, COMPLETED)")
    public ResponseEntity<List<TripResponse>> filterTripsByStatus(@RequestParam Trip.Status status) {
        List<Trip> trips = tripService.filterTripsByStatus(status);
        List<TripResponse> response = trips.stream()
                .map(TripResponse::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
    
    // 8. Get Trips Between Dates (GET /api/trips/daterange?start=...&end=...)
    @GetMapping("/daterange")
    @Operation(summary = "Get trips between a start and end date")
    public ResponseEntity<List<TripResponse>> getTripsBetweenDates(
            @RequestParam LocalDate start,
            @RequestParam LocalDate end) {
        List<Trip> trips = tripService.getTripsBetweenDates(start, end);
        List<TripResponse> response = trips.stream()
                .map(TripResponse::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    // 9. Get Trip Summary (GET /api/trips/summary)
    @GetMapping("/summary")
    @Operation(summary = "Returns total trips, minimum price, maximum price, and average price.")
    public ResponseEntity<TripSummaryResponse> getTripSummary() {
        TripSummaryResponse summary = tripService.getTripSummary();
        return ResponseEntity.ok(summary);
    }
}