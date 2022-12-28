package com.kytc.bikeID.dto;

import com.kytc.bikeID.entity.enums.WarrantyWork;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Data
public class TechnicalPassportDto {
@Id
    private Integer id;

    private Integer bikeId;

    private LocalDate visitDate;

    private WarrantyWork warrantyWork;
}
