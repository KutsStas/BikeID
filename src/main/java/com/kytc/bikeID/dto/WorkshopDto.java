package com.kytc.bikeID.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class WorkshopDto {
    private Integer id;
    private Integer userId;
    private String workshopName;
    private String address;
    private String website;
    private Integer workshopPhoneNumber;
    private LocalDate salesDate;
    private Integer workshopRating;
    private String workshopReviews;
}
