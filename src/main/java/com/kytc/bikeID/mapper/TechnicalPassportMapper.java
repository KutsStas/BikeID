package com.kytc.bikeID.mapper;

import com.kytc.bikeID.dto.TechnicalPassportDto;
import com.kytc.bikeID.entity.TechnicalPassport;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TechnicalPassportMapper {

    TechnicalPassportDto toDto(TechnicalPassport technicalPassport);

    TechnicalPassport toTechnicalPassport(TechnicalPassportDto dto);

}
