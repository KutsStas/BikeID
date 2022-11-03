package com.kytc.bikeID.controller;

import com.kytc.bikeID.dto.UserDto;
import com.kytc.bikeID.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
//todo add logs  SLF4J
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserDto> getUser(@RequestParam Integer id) {

        UserDto dto = userService.getUserById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto dto) {

        UserDto dto1 = userService.updateUserById(dto);
        return new ResponseEntity<>(dto1, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers() {

        List<UserDto> userDtoList = userService.allUsersList();
        return new ResponseEntity<>(userDtoList, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteUser(@RequestParam Integer id) {

        userService.deleteById(id);
        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }

    @GetMapping("/check")
    public ResponseEntity<Boolean> checkExpirationCode(@RequestParam String code, String email) {

        return new ResponseEntity<>(userService.checkExpirationCode(email, code), HttpStatus.OK);
    }

    //todo add endpoint to enable user


}
