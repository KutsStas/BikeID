package com.kytc.bikeID.mapper;

import com.kytc.bikeID.dto.ReportDto;
import com.kytc.bikeID.entity.RepairReport;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReportMapper {

    ReportDto toDto(RepairReport report);

    RepairReport toEntity(ReportDto dto);

}
