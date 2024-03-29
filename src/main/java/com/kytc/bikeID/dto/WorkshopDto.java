package com.kytc.bikeID.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
@Data
public class WorkshopDto {
    @Id
    private Integer id;
    private Integer managerId;
    private String workshopName;
    private String address;
    private String website;
    private Integer workshopPhoneNumber;
    private LocalDate salesDate;
    private Integer workshopRating;
    private String workshopReviews;
}
