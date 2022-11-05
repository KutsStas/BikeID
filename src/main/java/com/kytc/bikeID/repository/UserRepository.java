package com.kytc.bikeID.repository;

import com.kytc.bikeID.entity.User;
import com.kytc.bikeID.entity.enums.RoleEnum;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    int countByEmail(String email);

    @Modifying
    @Transactional
    @Query(value = "Update users  set enable=true, role='USER' where email=:email", nativeQuery = true)
    void setEnable(@Param("email")String email);

    @Query("select u.role from User u where u.id=:id")
    Optional<RoleEnum> findRoleById(Integer id);


}
