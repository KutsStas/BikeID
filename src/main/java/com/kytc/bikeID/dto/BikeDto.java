package com.kytc.bikeID.dto;

import com.kytc.bikeID.entity.enums.LegalStatus;
import lombok.Data;

import java.io.Serializable;

@Data
public class BikeDto implements Serializable {
    private Integer userId;
    private Integer workshopId;
    private Integer id;
    private String bikeType;
    private String bikeBrand;
    private String bikeModel;
    private String bikeColor;
    private Integer frameSize;
    private Integer wheelSize;
    private String frameNumber;
    private Integer warranty;
    private LegalStatus legalStatus;

}
