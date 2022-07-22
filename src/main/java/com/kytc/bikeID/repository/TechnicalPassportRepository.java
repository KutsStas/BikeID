package com.kytc.bikeID.repository;

import com.kytc.bikeID.entity.TechnicalPassport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnicalPassportRepository extends JpaRepository<TechnicalPassport, Integer> {
}
