package com.kytc.bikeID.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kytc.bikeID.DtoBuilder;
import com.kytc.bikeID.dto.BikeDto;
import com.kytc.bikeID.exeption.GlobalControllerAdvice;
import com.kytc.bikeID.service.BikeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(MockitoExtension.class)
public class BikeControllerTest {

    private MockMvc mvc;

    private BikeDto dto;

    private List<BikeDto> dtoList;

    @Mock
    private BikeService service;

    @InjectMocks
    BikeController bikeController;


    @BeforeEach
    public void setup() {

        dto = DtoBuilder.buildBike();
        dtoList = DtoBuilder.buildBikeDtoList();

        mvc = MockMvcBuilders.standaloneSetup(bikeController)
                .setControllerAdvice(new GlobalControllerAdvice()).build();
    }

    @Test
    public void addBikeTest() throws Exception {

        when(service.addBike(dto)).thenReturn(dto.getId());
        mvc.perform(post("/bike")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(dto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(dto.getId()));
    }

    @Test
    public void addBikeWhenDoesNotExist() throws Exception {

        given(service.addBike(dto)).willThrow(new NoSuchElementException("Can't find User by ID " + dto.getUserId()));
        mvc.perform(post("/bike")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(dto)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void getBikeTest() throws Exception {

        when(service.getBikeById(dto.getId())).thenReturn(dto);
        mvc.perform(get("/bike?id=" + dto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(dto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(dto.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bikeType").value(dto.getBikeType()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bikeBrand").value(dto.getBikeBrand()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bikeModel").value(dto.getBikeModel()));
    }

    @Test
    public void getBikeWhenDoesNotExist() throws Exception {

        given(service.getBikeById(dto.getId())).willThrow(new NoSuchElementException("Can't find bike by id: " + dto.getId()));
        mvc.perform(get("/bike?id=" + dto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(dto)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void getAllBikeTest() throws Exception {

        when(service.allUsersBike()).thenReturn(dtoList);
        BikeDto expected = dtoList.get(0);
        mvc.perform(get("/bike/all")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(dtoList)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(expected.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].bikeType").value(expected.getBikeType()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].bikeBrand").value(expected.getBikeBrand()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].bikeModel").value(expected.getBikeModel()));
    }


    protected static String asJsonString(final Object obj) {

        try {
            return new ObjectMapper().registerModule(new JavaTimeModule())
                    .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                    .writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}