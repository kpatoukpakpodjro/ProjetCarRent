package com.amsd.repository;

import com.amsd.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByRentedFalse();
    @Query("SELECT c FROM Car c WHERE c.rented = false ORDER BY c.price ASC")
    List<Car> findAvailableCarsSortedByPrice();
}