package com.kytc.bikeID.controller;

import com.kytc.bikeID.dto.WorkshopDto;
import com.kytc.bikeID.service.WorkshopService;
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
@RequestMapping("/workshop")
@Slf4j
public class WorkshopController {

    private final WorkshopService workshopService;


    @PostMapping
    public ResponseEntity<Integer> addWorkshop(@Valid WorkshopDto dto) {

        log.info("Add workshop request.Dto:{} ", dto);
        Integer newWorkshop = workshopService.addWorkshop(dto);
        log.info("Workshop with id:{} added successfully.", newWorkshop);
        return new ResponseEntity<>(newWorkshop, HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<WorkshopDto> getWorkshop(@RequestParam Integer id) {

        log.info("Get workshop by id:{} request", id);
        WorkshopDto dto = workshopService.getWorkshopById(id);
        log.info("Get workshop by id:{} successfully", id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<WorkshopDto> updateWorkshopInfo(@Valid WorkshopDto dto) {

        log.info("Update workshop with id:{}  request ", dto.getId());
        WorkshopDto response = workshopService.updateWorkshopInfo(dto);
        log.info("Workshop with id:{} update successfully", response.getId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<WorkshopDto>> getAllWorkshop() {

        log.info("Get all workshops request");
        List<WorkshopDto> workshopDtoList = workshopService.allWorkshopList();
        log.info("Successfully get all workshops");
        return new ResponseEntity<>(workshopDtoList, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteWorkshop(@RequestParam Integer id) {

        log.info("Delete workshop by id:{} request", id);
        workshopService.deleteById(id);
        log.info("Delete workshop by id:{} Successfully", id);
        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }

}
