package com.kytc.bikeID.Interfase;

import com.kytc.bikeID.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Integer> {

}
