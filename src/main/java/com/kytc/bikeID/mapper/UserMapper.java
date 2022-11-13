package com.kytc.bikeID.mapper;

import com.kytc.bikeID.dto.UserDto;
import com.kytc.bikeID.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "confirmPassword", ignore = true)
    UserDto toDto(User user);

    User toUser(UserDto dto);

    List<UserDto> toDtoList(List<User> users);


}
