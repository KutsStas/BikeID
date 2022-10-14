package com.kytc.bikeID.mapper;

import com.kytc.bikeID.dto.UserDto;
import com.kytc.bikeID.entity.User;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-14T15:52:47+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( user.getId() );
        userDto.setName( user.getName() );
        userDto.setSurname( user.getSurname() );
        userDto.setPhoneNumber( user.getPhoneNumber() );
        userDto.setEmail( user.getEmail() );
        userDto.setEnable( user.isEnable() );

        return userDto;
    }

    @Override
    public User toUser(UserDto dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setId( dto.getId() );
        user.setName( dto.getName() );
        user.setSurname( dto.getSurname() );
        user.setPhoneNumber( dto.getPhoneNumber() );
        user.setEmail( dto.getEmail() );
        user.setPassword( dto.getPassword() );
        user.setEnable( dto.isEnable() );

        return user;
    }
}
