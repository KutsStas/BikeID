package com.kytc.bikeID.dto;

import lombok.Data;

@Data
public class BikeDto {
    private String bikeType;
    private String bikeBrand;
    private String bikeModel;
    private String bikeColor;
    private Integer frameSize;
    private Integer wheelSize;
    private String frameNumber;
    private Integer warranty;

}
