package com.kytc.bikeID.repository;

import com.kytc.bikeID.entity.TechnicalPassport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TechnicalPassportRepository extends JpaRepository<TechnicalPassport, Integer> {

    @Query("select t from TechnicalPassport t where t.bike.id =:passportId")
    List<TechnicalPassport> findAllByBikeId(Integer passportId);

}
