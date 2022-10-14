package com.kytc.bikeID.controller;

import com.kytc.bikeID.dto.TechnicalPassportDto;
import com.kytc.bikeID.service.TechnicalPassportService;
import lombok.RequiredArgsConstructor;
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
public class TechnicalPassportController {

    private final TechnicalPassportService technicalPassportService;

    @PostMapping
    public ResponseEntity<Integer> addTechnicalPassport(@Valid TechnicalPassportDto dto) {

        Integer newTechnicalPassport = technicalPassportService.addTechnicalPassport(dto);
        return new ResponseEntity<>(newTechnicalPassport, HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<TechnicalPassportDto> getTechnicalPassport(@RequestParam Integer id) {

        TechnicalPassportDto dto = technicalPassportService.getTechnicalPassportById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<TechnicalPassportDto> updateTechnicalPassportInfo(@Valid TechnicalPassportDto dto) {

        TechnicalPassportDto response = technicalPassportService.updateTechnicalPassportInfo(dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TechnicalPassportDto>> getAllBikeTechnicalPassport(@RequestParam Integer passportId) {

        List<TechnicalPassportDto> technicalPassportDtoList = technicalPassportService
                .allBikesTechnicalPassport(passportId);
        return new ResponseEntity<>(technicalPassportDtoList, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteTechnicalPassport(@RequestParam Integer id) {

        technicalPassportService.deleteTechnicalPassportById(id);
        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }

}
