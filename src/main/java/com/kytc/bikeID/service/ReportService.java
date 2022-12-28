package com.kytc.bikeID.service;

import com.kytc.bikeID.dto.ReportDto;
import com.kytc.bikeID.entity.RepairReport;

import java.util.List;

public interface ReportService {


    List<RepairReport> getAllBikeReport(Integer bikeId);


    List<RepairReport> getAllUserReport();

    RepairReport addReport(ReportDto dto);


    RepairReport updateReportById(ReportDto dto);

    void deleteReportByID(String id);

}
