package com.kytc.bikeID.controller;

import com.kytc.bikeID.dto.BikeDto;
import com.kytc.bikeID.service.BikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/bike")
@Slf4j
public class BikeController {

    private final BikeService bikeService;

    @PostMapping
    public ResponseEntity<Integer> addBike(@RequestBody @Valid BikeDto dto) {

        log.info("Add bike request.Dto:{} ", dto);
        Integer newBike = bikeService.addBike(dto);
        log.info("Bike with id:{} added successfully.", newBike);
        return new ResponseEntity<>(newBike, HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<BikeDto> getBike(@RequestParam Integer id) {

        log.info("Get bike by id:{} request", id);
        BikeDto dto = bikeService.getBikeById(id);
        log.info("Get bike by id:{} successfully", id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<BikeDto> updateBikeInfo(@RequestBody @Valid BikeDto dto) {

        log.info("Update bike with id:{}  request ", dto.getId());
        BikeDto response = bikeService.updateBikeById(dto);
        log.info("Bike with id:{} update successfully", response.getId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/legal")
    public ResponseEntity<BikeDto> updateBikeLegalStatus(@RequestParam Integer id) {

        log.info("Update legal status bike with id:{}  request ", id);
        BikeDto response = bikeService.updateBikeLegalStatus(id);
        log.info("Bike with id:{} update legal status successfully", id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<BikeDto>> getAllUsersBike() {

        log.info("Getting all users bikes request");
        List<BikeDto> bikeDtoList = bikeService.allUsersBike();
        log.info("Successfully getting all users bike");
        return new ResponseEntity<>(bikeDtoList, HttpStatus.OK);
    }

    @GetMapping("/check_all")
    public ResponseEntity<List<BikeDto>> getAllOfStolenBikes() {

        log.info("Getting all users bikes request");
        List<BikeDto> bikeDtoList = bikeService.listOfStolenBikes();
        log.info("Successfully getting all users bike");
        return new ResponseEntity<>(bikeDtoList, HttpStatus.OK);
    }

    @GetMapping("/check")
    public ResponseEntity<String> checkBikeLegalStatus(@RequestParam Integer id) {

        log.info("Check bike legal status by id:{} request", id);
        String result = bikeService.checkBikeLegalStatus(id);
        log.info("Check bike legal status by id:{} successfully", id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @DeleteMapping
    public ResponseEntity<Boolean> deleteBike(@RequestParam Integer id) {

        log.info("Delete bike by id:{} request", id);
        bikeService.deleteBikeById(id);
        log.info("Delete bike by id:{} Successfully", id);
        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }

}
