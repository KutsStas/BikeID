package com.kytc.bikeID.repository;

import com.kytc.bikeID.entity.Bike;
import com.kytc.bikeID.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BikeRepository extends JpaRepository<Bike, Integer> {

    List<Bike> findAllByUser(User user);

}
