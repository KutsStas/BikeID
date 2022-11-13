package com.kytc.bikeID.controller;

import com.kytc.bikeID.dto.TechnicalPassportDto;
import com.kytc.bikeID.service.TechnicalPassportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/technicalPassport")
@Slf4j
public class TechnicalPassportController {

    private final TechnicalPassportService technicalPassportService;

    @PostMapping
    public ResponseEntity<Integer> addTechnicalPassport(@Valid TechnicalPassportDto dto) {

        log.info("Add technical passport request.Dto:{} ", dto);
        Integer newTechnicalPassport = technicalPassportService.addTechnicalPassport(dto);
        log.info("Technical passport with id:{} added successfully.", newTechnicalPassport);

        return new ResponseEntity<>(newTechnicalPassport, HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<TechnicalPassportDto> getTechnicalPassport(@RequestParam Integer id) {

        log.info("Get  by id:{} request", id);
        TechnicalPassportDto dto = technicalPassportService.getTechnicalPassportById(id);
        log.info("Get technical passport by id:{} successfully", id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TechnicalPassportDto>> getAllBikesTechnicalPassport(@RequestParam Integer id) {

        log.info("Get all technical passport request");
        List<TechnicalPassportDto> technicalPassportDtoList = technicalPassportService.allBikesTechnicalPassport(id);
        log.info("Successfully get all technical passport");

        return new ResponseEntity<>(technicalPassportDtoList, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<TechnicalPassportDto> updateTechnicalPassportInfo(@Valid TechnicalPassportDto dto) {

        log.info("Update technical passport with id:{}  request ", dto.getId());
        TechnicalPassportDto response = technicalPassportService.updateTechnicalPassportInfo(dto);
        log.info("Technical passport with id:{} update successfully", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteTechnicalPassport(@RequestParam Integer id) {

        log.info("Delete technical passport by id:{} request", id);
        technicalPassportService.deleteTechnicalPassportById(id);
        log.info("Delete technical passport by id:{} Successfully", id);
        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }

}
