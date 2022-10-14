package com.kytc.bikeID.mapper;

import com.kytc.bikeID.dto.WorkshopDto;
import com.kytc.bikeID.entity.Workshop;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkshopMapper {

    WorkshopDto toDto(Workshop workshop);

    Workshop toWorkshop(WorkshopDto workshopDto);

}
