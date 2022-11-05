package com.kytc.bikeID.repository;

import com.kytc.bikeID.entity.Bike;
import com.kytc.bikeID.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BikeRepository extends JpaRepository<Bike, Integer> {

    List<Bike> findAllByUser(User user);

@Query(value = "select * from bikes where legal_status='ILLEGAL'", nativeQuery = true)
    List<Bike> findAllByRole();

    Bike findByUser(User user);


}
