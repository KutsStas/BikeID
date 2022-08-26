package com.kytc.bikeID.service.impl;

import com.kytc.bikeID.dto.UserDto;
import com.kytc.bikeID.entity.User;
import com.kytc.bikeID.exeption.ValidationException;
import com.kytc.bikeID.mapper.UserMapper;
import com.kytc.bikeID.repository.UserRepository;
import com.kytc.bikeID.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public Integer addUser(UserDto dto) {
        validateEmail(dto.getEmail());
        User user = userMapper.toUser(dto);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        userRepository.save(user);
        return user.getId();

    }

    private void validateEmail(String email) {
        if (userRepository.countByEmail(email) > 0) {
            throw new ValidationException("User with email " + email + " already exist");
        }

    }

    @Override
    public UserDto getUserById(Integer id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Can't find user by id: " + id));

        return userMapper.toDto(user);
    }

    private UserDto mapToDto(User user) {

        return userMapper.toDto(user);
    }

    @Override
    public UserDto updateUserById(UserDto dto) {

        if (isNull(dto.getId())) {
            throw new ValidationException("id can't be null");
        }
        userRepository.findById(dto.getId())
                .orElseThrow(() -> new NoSuchElementException("Can't find user by id: " + dto.getId()));
        User user = userMapper.toUser(dto);
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
