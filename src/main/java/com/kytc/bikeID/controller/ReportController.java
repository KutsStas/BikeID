package com.kytc.bikeID.controller;


import com.kytc.bikeID.dto.ReportDto;
import com.kytc.bikeID.entity.RepairReport;
import com.kytc.bikeID.service.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/report")
@Slf4j
public class ReportController {

    private final ReportService reportService;

    @PostMapping
    public ResponseEntity<RepairReport> addReport(@Valid ReportDto dto) {

        log.info("Adding report request");
        RepairReport result = reportService.addReport(dto);
        log.info("Adding report successfully");
        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @GetMapping("/by_user")
    public ResponseEntity<List<RepairReport>> getAllUsersReport() {

        log.info("Getting reports by user request");
        List<RepairReport> reportList = reportService.getAllUserReport();
        log.info("Successfully getting all reports by user");
        return new ResponseEntity<>(reportList, HttpStatus.OK);
    }

    @GetMapping("/by_bike")
    public ResponseEntity<List<RepairReport>> getAllBikesReport(@RequestParam Integer bikeId) {

        log.info("Getting reports by bike with id " + bikeId + " request");
        List<RepairReport> reportList = reportService.getAllBikeReport(bikeId);
        log.info("Successfully getting all reports by bike id " + bikeId);
        return new ResponseEntity<>(reportList, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<RepairReport> updateReportInfo(@Valid ReportDto dto) {

        log.info("Update report with id: " + dto.getId() + " request");
        RepairReport report = reportService.updateReportById(dto);
        log.info("Report with id: " + dto.getId() + " update successfully");
        return new ResponseEntity<>(report, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteReport(@RequestParam String id) {

        log.info("Delete report with id: " + id + " request");
        reportService.deleteReportByID(id);
        log.info("Delete with id: " + id + " update successfully");
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
