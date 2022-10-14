package com.kytc.bikeID.service;

import com.kytc.bikeID.dto.WorkshopDto;

import java.util.List;

public interface WorkshopService {

    Integer addWorkshop(WorkshopDto workshopDto);

    WorkshopDto getWorkshopById(Integer id);

    WorkshopDto updateWorkshopInfo(WorkshopDto dto);

    List<WorkshopDto> allWorkshopList();

    void deleteById(Integer id);

}
