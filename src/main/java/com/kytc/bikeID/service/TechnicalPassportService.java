package com.kytc.bikeID.service;

import com.kytc.bikeID.dto.TechnicalPassportDto;

import java.util.List;

public interface TechnicalPassportService {

    Integer addTechnicalPassport(TechnicalPassportDto dto);

    TechnicalPassportDto getTechnicalPassportById(Integer id);

    TechnicalPassportDto updateTechnicalPassportInfo(TechnicalPassportDto dto);


    List<TechnicalPassportDto> allBikesTechnicalPassport(Integer bikeId);

    void deleteTechnicalPassportById(Integer id);


}
