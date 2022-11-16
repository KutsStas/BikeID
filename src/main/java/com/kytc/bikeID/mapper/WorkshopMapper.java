package com.kytc.bikeID.mapper;

import com.kytc.bikeID.dto.WorkshopDto;
import com.kytc.bikeID.entity.Workshop;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WorkshopMapper {

    WorkshopDto toDto(Workshop workshop);

    Workshop toWorkshop(WorkshopDto workshopDto);

    List<WorkshopDto> toDtoList(List<Workshop> workshops);

}
