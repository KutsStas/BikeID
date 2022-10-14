package com.kytc.bikeID.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Data
public class TechnicalPassportDto {
@Id
    private Integer id;

    private Integer passportId;

    private LocalDate visitDate;

    private String bikeName;

    private String serviceWork;

    private Boolean warrantyWork;

    private String workShop;

    private String clientName;

    private String servicemenName;

    private String replacementParts;

    private Integer workPrise;

    private String technicalStatus;

    private String serviceMenComments;


}
