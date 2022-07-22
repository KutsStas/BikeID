package com.kytc.bikeID.service;

import com.kytc.bikeID.dto.UserDto;

import java.util.List;

public interface UserService {

    Integer addUser(UserDto dto);

    UserDto getUserById(Integer id);

    UserDto updateUserById(UserDto dto);

    List<UserDto> allUsersList();

    void deleteById(Integer id);

}
