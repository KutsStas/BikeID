package com.kytc.bikeID.repository;

import com.kytc.bikeID.entity.RepairReport;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface RepairReportRepository extends MongoRepository<RepairReport, String> {


    List<RepairReport> findAllByBikeId(Integer bikeId);

    List<RepairReport> findAllByUserId(Integer userId);

}
