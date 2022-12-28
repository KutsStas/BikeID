package com.kytc.bikeID.service.impl;

import com.kytc.bikeID.dto.ReportDto;
import com.kytc.bikeID.entity.RepairReport;
import com.kytc.bikeID.entity.User;
import com.kytc.bikeID.exception.ValidationException;
import com.kytc.bikeID.mapper.ReportMapper;
import com.kytc.bikeID.repository.RepairReportRepository;
import com.kytc.bikeID.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final RepairReportRepository reportRepository;

    private final ReportMapper reportMapper;


    @Override
    public List<RepairReport> getAllBikeReport(Integer bikeId) {

        if (isNull(bikeId)) {
            throw new ValidationException("Bike id can't be null");
        }
        return reportRepository.findAllByBikeId(bikeId);
    }


    @Override
    public List<RepairReport> getAllUserReport() {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return reportRepository.findAllByUserId(user.getId());
    }

    @Override
    public RepairReport addReport(ReportDto dto) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        dto.setUserId(user.getId());
        RepairReport report = reportMapper.toEntity(dto);
        reportRepository.save(report);
        return report;
    }

    @Override
    public RepairReport updateReportById(ReportDto dto) {

        reportRepository.findById(dto.getId().toString()).orElseThrow(() ->
                new NoSuchElementException("Can't find report by id:" + dto.getId().toString()));
        RepairReport repairReport = reportMapper.toEntity(dto);
        reportRepository.save(repairReport);
        return repairReport;
    }

    @Override
    public void deleteReportByID(String id) {

        reportRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Can't find report by id:" + id));
        reportRepository.deleteById(id);
    }

}
