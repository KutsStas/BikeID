package com.kytc.bikeID.controller;

import com.kytc.bikeID.dto.WorkshopDto;
import com.kytc.bikeID.service.WorkshopService;
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
@RequestMapping("/workshop")
public class WorkshopController {

    private final WorkshopService workshopService;


    @PostMapping
    public ResponseEntity<Integer> addWorkshop(@Valid WorkshopDto dto) {

        Integer newWorkshop = workshopService.addWorkshop(dto);
        return new ResponseEntity<>(newWorkshop, HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<WorkshopDto> getWorkshop(@RequestParam Integer id) {

        WorkshopDto dto = workshopService.getWorkshopById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<WorkshopDto> updateWorkshopInfo(@Valid WorkshopDto dto) {

        WorkshopDto response = workshopService.updateWorkshopInfo(dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<WorkshopDto>> getAllUsersWorkshop() {

        List<WorkshopDto> workshopDtoList = workshopService.allWorkshopList();
        return new ResponseEntity<>(workshopDtoList, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteWorkshop(@RequestParam Integer id) {

        workshopService.deleteById(id);
        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }

}
