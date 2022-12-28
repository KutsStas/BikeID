package com.kytc.bikeID.mapper;

import com.kytc.bikeID.dto.ReportDto;
import com.kytc.bikeID.entity.RepairReport;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-28T17:04:12+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class ReportMapperImpl implements ReportMapper {

    @Override
    public ReportDto toDto(RepairReport report) {
        if ( report == null ) {
            return null;
        }

        ReportDto reportDto = new ReportDto();

        reportDto.setId( report.getId() );
        reportDto.setBikeId( report.getBikeId() );
        reportDto.setUserId( report.getUserId() );
        reportDto.setBikeName( report.getBikeName() );
        reportDto.setServiceWork( report.getServiceWork() );
        reportDto.setWarrantyWork( report.getWarrantyWork() );
        reportDto.setWorkShop( report.getWorkShop() );
        reportDto.setClientName( report.getClientName() );
        reportDto.setServicemenName( report.getServicemenName() );
        reportDto.setReplacementParts( report.getReplacementParts() );
        reportDto.setWorkPrise( report.getWorkPrise() );
        reportDto.setTechnicalStatus( report.getTechnicalStatus() );
        reportDto.setServiceMenComments( report.getServiceMenComments() );

        return reportDto;
    }

    @Override
    public RepairReport toEntity(ReportDto dto) {
        if ( dto == null ) {
            return null;
        }

        RepairReport repairReport = new RepairReport();

        repairReport.setId( dto.getId() );
        repairReport.setBikeId( dto.getBikeId() );
        repairReport.setUserId( dto.getUserId() );
        repairReport.setBikeName( dto.getBikeName() );
        repairReport.setServiceWork( dto.getServiceWork() );
        repairReport.setWarrantyWork( dto.getWarrantyWork() );
        repairReport.setWorkShop( dto.getWorkShop() );
        repairReport.setClientName( dto.getClientName() );
        repairReport.setServicemenName( dto.getServicemenName() );
        repairReport.setReplacementParts( dto.getReplacementParts() );
        repairReport.setWorkPrise( dto.getWorkPrise() );
        repairReport.setTechnicalStatus( dto.getTechnicalStatus() );
        repairReport.setServiceMenComments( dto.getServiceMenComments() );

        return repairReport;
    }
}
