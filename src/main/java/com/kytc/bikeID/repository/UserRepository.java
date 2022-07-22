package com.kytc.bikeID.repository;

import com.kytc.bikeID.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
