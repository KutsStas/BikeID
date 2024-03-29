package com.kytc.bikeID.mapper;

import com.kytc.bikeID.dto.BikeDto;
import com.kytc.bikeID.entity.Bike;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BikeMapper {

    BikeDto toDto(Bike bike);

    Bike toEntity(BikeDto dto);

    List<BikeDto> toDtoList(List<Bike> bikes);

}
