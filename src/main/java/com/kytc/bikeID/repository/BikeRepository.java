package com.kytc.bikeID.repository;

import com.kytc.bikeID.entity.Bike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BikeRepository extends JpaRepository<Bike, Integer> {
}
