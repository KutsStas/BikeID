package com.kytc.bikeID.service;

import com.kytc.bikeID.DtoBuilder;
import com.kytc.bikeID.EntityBuilder;
import com.kytc.bikeID.dto.UserDto;
import com.kytc.bikeID.entity.User;
import com.kytc.bikeID.entity.enums.RoleEnum;
import com.kytc.bikeID.exception.ValidationException;
import com.kytc.bikeID.mapper.UserMapperImpl;
import com.kytc.bikeID.repository.UserRepository;
import com.kytc.bikeID.service.impl.UserServiceImpl;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class UserServiceTest {

    private static final String AUTH_KEY = "AUTH_KEY_";

    private static final int ONE_OUR = 3600;

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserRepository userRepository;

    @Mock
    UserMapperImpl userMapper;

    @Mock
    EmailSenderService emailSenderService;

    @Mock
    CacheService cacheService;


    @Test
    public void addUserTest() {

        User user = EntityBuilder.buildUser();
        UserDto userDto = DtoBuilder.buildUserDto();
        when(userMapper.toUser(userDto)).thenReturn(user);

        int id = userService.addUser(userDto);
        assertEquals(user.getId(), id);
        verify(userRepository, times(1)).save(user);
        verify(cacheService, times(1)).addValueExpired(eq(AUTH_KEY + user.getEmail()), anyString(), eq(ONE_OUR));
        verify(emailSenderService, times(1)).sendAuthenticationCode(eq(user.getEmail()), eq(user.getName()), anyString());
    }

    @Test
    public void addUserWithInvalidEmailTest() {

        UserDto userDto = DtoBuilder.buildUserDto();
        when(userRepository.countByEmail(userDto.getEmail())).thenReturn(2);

        ValidationException thrown = assertThrows(ValidationException.class, () ->
                userService.addUser(userDto));
        assertEquals(thrown.getMessage(), "User with email " + userDto.getEmail() + " already exist");

    }

    @Test
    public void getUserByIdTest() {

        User user = EntityBuilder.buildUser();
        UserDto expected = DtoBuilder.buildUserDto();
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(userMapper.toDto(user)).thenReturn(expected);

        UserDto actual = userService.getUserById(user.getId());

        assertEquals(actual.getEmail(), expected.getEmail());
        assertEquals(actual.getPassword(), expected.getPassword());
        assertEquals(actual.getName(), expected.getName());
        verify(userRepository, times(1)).findById(user.getId());
        verify(userMapper, times(1)).toDto(user);
    }

    @Test
    public void getUserByIdWhenIdIsNone() {


        NoSuchElementException thrown = assertThrows(java.util.NoSuchElementException.class, () ->
                userService.getUserById(2));
        assertEquals(thrown.getMessage(), "Can't find user by id: " + 2);
    }

    @Test
    public void updateUserByIdTest() {

        User user = EntityBuilder.buildUser();
        UserDto expected = DtoBuilder.buildUserDto();
        when(userRepository.findById(expected.getId())).thenReturn(Optional.of(user));
        expected.setName("Test Name");
        expected.setEmail("test@Mail");
        expected.setPhoneNumber("911");
        when(userMapper.toUser(expected)).thenReturn(user);
        UserDto actual = userService.updateUserById(expected);
        assertEquals("test@Mail", actual.getEmail());
        assertEquals("Test Name", actual.getName());
        assertEquals("911", actual.getPhoneNumber());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void updateUserByIdWhenIdNullTest() {

        UserDto expected = DtoBuilder.buildUserDto();
        expected.setId(null);
        ValidationException thrown = assertThrows(ValidationException.class, () ->
                userService.updateUserById(expected));
        assertEquals(thrown.getMessage(), "User id can't be null");

    }
    @Test
    public void updateUserByIdWhenIdWrong() {
        UserDto expected = DtoBuilder.buildUserDto();
        NoSuchElementException thrown = assertThrows(java.util.NoSuchElementException.class, () ->
                userService.updateUserById(expected));
        assertEquals(thrown.getMessage(), "Can't find user by id: " + expected.getId());

    }

        @Test
    public void setAdminTest() {

        User user = EntityBuilder.buildUser();
        user.setEnable(true);
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        boolean result = userService.setAdmin(user.getId());
        assertTrue(result);
        assertEquals(user.getRole(), RoleEnum.ADMIN);
        verify(userRepository, times(1)).save(user);

    }
    @Test
    public void setAdminWithWrongIdTest() {

        NoSuchElementException thrown = assertThrows(java.util.NoSuchElementException.class, () ->
                userService.setAdmin(2));
        assertEquals(thrown.getMessage(), "Can't find user by id: " + 2);
    }

    @Test
    public void setAdminIsNotEnableTest() {

        User user = EntityBuilder.buildUser();
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        ValidationException thrown = assertThrows(ValidationException.class, () -> {
            userService.setAdmin(user.getId());
            ;
        });
        assertEquals(thrown.getMessage(), "User with id" + user.getId() + "is not enable");

    }

    @Test
    public void allUsersListTest() {

        List<User> list = EntityBuilder.buildUserList();
        List<UserDto> expectedList = DtoBuilder.buildUserListDto();

        when(userRepository.findAll()).thenReturn(list);
        when(userMapper.toDtoList(list)).thenReturn(expectedList);

        List<UserDto> actualList = userService.allUsersList();

        assertEquals(expectedList.size(), actualList.size());
        verify(userRepository, times(1)).findAll();

    }

    @Test
    public void deleteById() {

        Integer id = RandomUtils.nextInt();
        userService.deleteById(id);

        verify(userRepository, times(1)).deleteById(id);

    }

    @Test
    public void checkExpirationCodeTest() {

        User user = EntityBuilder.buildUser();
        when(cacheService.getValueByKey(AUTH_KEY + user.getEmail())).thenReturn("keyFromDb");
        boolean result = userService.checkExpirationCode(user.getEmail(), "keyFromDb");

        verify(userRepository, times(1)).setEnable(user.getEmail());
        assertTrue(result);
    }

    @Test
    public void checkExpirationCodeWithNullValueKeyTest() {

        User user = EntityBuilder.buildUser();
        ValidationException thrown = assertThrows(ValidationException.class, () -> {
            boolean result =    userService.checkExpirationCode(user.getEmail(), null);
        });
        verify(userRepository, never()).setEnable(user.getEmail());
        assertEquals(thrown.getMessage(), "Can't find user code by email " + user.getEmail());
    }

    @Test
    public void checkExpirationCodeWithEqualsCodeTest() {

        User user = EntityBuilder.buildUser();
        when(cacheService.getValueByKey(AUTH_KEY + user.getEmail())).thenReturn("keyFromDb");
        String wrongCode = "AnyKey";

        ValidationException thrown = assertThrows(ValidationException.class, () -> {
            userService.checkExpirationCode(user.getEmail(), wrongCode);
        });
        assertEquals(thrown.getMessage(), "This code is wrong!" + wrongCode);
    }

    @Test
    public void changeExpirationDate() {

    }

}
