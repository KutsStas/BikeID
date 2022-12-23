package com.kytc.bikeID.controller;

import com.kytc.bikeID.dto.UserDto;
import com.kytc.bikeID.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Integer> addUser(@RequestBody @Valid UserDto dto) {

        log.info("Add user request.Dto:{} ", dto);
        Integer newUser = userService.addUser(dto);
        log.info("User with id:{} added successfully.", newUser);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<UserDto> getUser(@RequestParam Integer id) {

        log.info("Get user by id:{} request", id);
        UserDto dto = userService.getUserById(id);
        log.info("Get user by id:{} successfully", id);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto dto) {

        log.info("Update user with id:{}  request ", dto.getId());
        UserDto dto1 = userService.updateUserById(dto);
        log.info("User with id:{} update successfully", dto1.getId());

        return new ResponseEntity<>(dto1, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers() {

        log.info("Get all users request");
        List<UserDto> userDtoList = userService.allUsersList();
        log.info("Successfully get all users");

        return new ResponseEntity<>(userDtoList, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteUser(@RequestParam Integer id) {

        log.info("Delete user by id:{} request", id);
        userService.deleteById(id);
        log.info("Delete user by id:{} successfully", id);

        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }

    @PutMapping("/admin")
    public ResponseEntity<Boolean> setUserToAdmin(@RequestParam Integer id) {

        log.info("Set user with id:{} to admin role request", id);
        userService.setAdmin(id);
        log.info("Set user with id:{} to admin role successfully", id);

        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }

    @GetMapping("/check")
    public ResponseEntity<Boolean> checkExpirationCode(@RequestParam String code, String email) {

        return new ResponseEntity<>(userService.checkExpirationCode(email, code), HttpStatus.OK);
    }


}
