package com.kytc.bikeID;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kytc.bikeID.dto.BikeDto;
import com.kytc.bikeID.dto.TechnicalPassportDto;
import com.kytc.bikeID.dto.UserDto;
import com.kytc.bikeID.dto.WorkshopDto;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DtoBuilder {

    public static UserDto buildUserDto() {

        UserDto userDto = new UserDto();

        userDto.setId(RandomUtils.nextInt());
        userDto.setName("Name");
        userDto.setPassword(RandomStringUtils.randomAlphabetic(7));
        userDto.setEmail(RandomStringUtils.random(5) + "@mail.com");
        return userDto;
    }

    public static BikeDto buildBike() {

        BikeDto bikeDto = new BikeDto();
        bikeDto.setId(RandomUtils.nextInt());
        bikeDto.setBikeBrand(RandomStringUtils.random(5));
        bikeDto.setBikeModel(RandomStringUtils.random(6));
        bikeDto.setBikeColor(RandomStringUtils.random(7));
        bikeDto.setBikeType(RandomStringUtils.random(8));
        return bikeDto;
    }


    public static WorkshopDto buildWorkshopDto() {

        WorkshopDto workshopDto = new WorkshopDto();
        workshopDto.setId(RandomUtils.nextInt());
        workshopDto.setWorkshopPhoneNumber(RandomUtils.nextInt());
        workshopDto.setWorkshopName(RandomStringUtils.random(5));


        return workshopDto;
    }
    public static TechnicalPassportDto buildTechnicalPassportDto () {
        TechnicalPassportDto technicalPassportDto = new TechnicalPassportDto();
        technicalPassportDto.setId(RandomUtils.nextInt());
        technicalPassportDto.setTechnicalStatus(RandomStringUtils.random(5));
        technicalPassportDto.setClientName(RandomStringUtils.random(5));
        return technicalPassportDto;
    }

    public static List<UserDto> buildUserListDto() {

        List<UserDto> usersDto = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            usersDto.add(buildUserDto());
        }
        return usersDto;
    }

    public static List<BikeDto> buildBikeDtoList() {

        List<BikeDto> bikes = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            bikes.add(buildBike());
        }
        return bikes;
    }

    public static List<WorkshopDto> buildWorkshopDtoList() {

        List<WorkshopDto> workshopDtos = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            workshopDtos.add(buildWorkshopDto());
        }
        return workshopDtos;
    }

    public static List<TechnicalPassportDto> buildTechnicalPassportDtoList() {

        List<TechnicalPassportDto> technicalPassportDtos = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            technicalPassportDtos.add(buildTechnicalPassportDto());
        }
        return technicalPassportDtos;
    }
    public static String asJsonString(final Object obj) {

        try {
            return new ObjectMapper().registerModule(new JavaTimeModule())
                    .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                    .writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
