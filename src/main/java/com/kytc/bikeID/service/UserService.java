package com.kytc.bikeID.service;

import com.kytc.bikeID.dto.UserDto;
import com.kytc.bikeID.entity.User;

import java.time.LocalDate;
import java.util.List;

public interface UserService {

    Integer addUser(UserDto dto);

    UserDto getUserById(Integer id);

    UserDto updateUserById(UserDto dto);

    boolean setAdmin(Integer id);

    List<UserDto> allUsersList();

    void deleteById(Integer id);

    boolean checkExpirationCode(String email, String key);

    void changeExpirationDate(User user, LocalDate newExpirationDate);

    String generateKey();

}
