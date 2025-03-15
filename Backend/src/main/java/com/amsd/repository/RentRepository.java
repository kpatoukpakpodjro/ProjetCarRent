package com.amsd.repository;

import com.amsd.model.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RentRepository extends JpaRepository<Rent, Long> {
    @Query("SELECT r FROM Rent r WHERE r.car.id = :carId AND r.endDate IS NULL")
    Optional<Rent> findActiveRentByCarId(@Param("carId") Long carId);

    @Query("SELECT r FROM Rent r WHERE r.user.id = :userId")
    List<Rent> findByUserId(@Param("userId") Long userId);

}

