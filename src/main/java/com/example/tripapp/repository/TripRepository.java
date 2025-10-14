package com.example.tripapp.repository;

import com.example.tripapp.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {

    // 6. Search Trips by Destination (CASE-INSENSITIVE and partial match)
    List<Trip> findByDestinationContainingIgnoreCase(String destination);

    // 7. Filter Trips by Status
    List<Trip> findByStatus(Trip.Status status);
    
    // 8. Get Trips Between Dates
    // Find trips where the start date is >= start and end date is <= end
    @Query("SELECT t FROM Trip t WHERE t.startDate >= :start AND t.endDate <= :end")
    List<Trip> findTripsBetweenDates(@Param("start") LocalDate start, @Param("end") LocalDate end);
    
    // 9. Get Trip Summary
    @Query(value = """
        SELECT 
            COUNT(t.id) AS totalTrips,
            MIN(t.price) AS minPrice,
            MAX(t.price) AS maxPrice,
            AVG(t.price) AS averagePrice
        FROM 
            trips t
    """, nativeQuery = true)
    Map<String, Object> getTripSummary();
}