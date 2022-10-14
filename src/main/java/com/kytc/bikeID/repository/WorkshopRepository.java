package com.kytc.bikeID.repository;

import com.kytc.bikeID.entity.User;
import com.kytc.bikeID.entity.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkshopRepository extends JpaRepository<Workshop, Integer> {

    List<Workshop> findAllByUser(User user);

}
