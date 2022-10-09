package com.kytc.bikeID.service;

import com.kytc.bikeID.dto.BikeDto;

import java.util.List;

public interface BikeService {

    Integer addBike(BikeDto dto);

    BikeDto getBikeById(Integer id);

    BikeDto updateBikeById(BikeDto dto);


    List<BikeDto> allUsersBike();

    void deleteBikeById(Integer id);



}
