package com.kytc.bikeID.controller;

import com.kytc.bikeID.dto.BikeDto;
import com.kytc.bikeID.service.BikeService;
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
@RequestMapping("/bike")
public class BikeController {

    private final BikeService bikeService;

    @PostMapping
    public ResponseEntity<Integer> addBike(@Valid BikeDto dto) {

        Integer newBike = bikeService.addBike(dto);
        return new ResponseEntity<>(newBike, HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<BikeDto> getBike(@RequestParam Integer id) {

        BikeDto dto = bikeService.getBikeById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<BikeDto> updateBikeInfo(@Valid BikeDto dto) {

        BikeDto response = bikeService.updateBikeById(dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<BikeDto>> getAllUsersBike() {

        List<BikeDto> bikeDtoList = bikeService.allUsersBike();
        return new ResponseEntity<>(bikeDtoList, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteBike(@RequestParam Integer id) {

        bikeService.deleteBikeById(id);
        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }

}
