package com.kytc.bikeID.service.impl;

import com.kytc.bikeID.dto.UserDto;
import com.kytc.bikeID.entity.User;
import com.kytc.bikeID.exeption.ValidationException;
import com.kytc.bikeID.repository.UserRepository;
import com.kytc.bikeID.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public Integer addUser(UserDto dto) {

        User user = new User();
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setConfirmPassword(dto.getConfirmPassword());
        userRepository.save(user);
        return user.getId();

    }

    @Override
    public UserDto getUserById(Integer id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Can't find user by id: " + id));

        return mapToDto(user);
    }

    private UserDto mapToDto(User user) {

        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setSurname(user.getSurname());
        dto.setEmail(user.getEmail());
        dto.setPhoneNumber(user.getPhoneNumber());
        return dto;
    }

    @Override
    public UserDto updateUserById(UserDto dto) {

        if (isNull(dto.getId())) {
            throw new ValidationException("id can't be null");
        }
        User user = userRepository.findById(dto.getId())
                .orElseThrow(() -> new NoSuchElementException("Can't find user by id: " + dto.getId()));
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setConfirmPassword(dto.getConfirmPassword());
        userRepository.save(user);
        return dto;
    }

    @Override
    public List<UserDto> allUsersList() {

        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());

    }

    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

}
