package com.kytc.bikeID.service.impl;

import com.kytc.bikeID.dto.UserDto;
import com.kytc.bikeID.entity.User;
import com.kytc.bikeID.entity.enums.RoleEnum;
import com.kytc.bikeID.exeption.ValidationException;
import com.kytc.bikeID.mapper.UserMapper;
import com.kytc.bikeID.repository.UserRepository;
import com.kytc.bikeID.service.CacheService;
import com.kytc.bikeID.service.EmailSenderService;
import com.kytc.bikeID.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final String AUTH_KEY = "AUTH_KEY_";

    private static final int ONE_OUR = 3600;

    private final EmailSenderService emailSenderService;

    private final CacheService cacheService;

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public Integer addUser(UserDto dto) {

        validateEmail(dto.getEmail());
        User user = userMapper.toUser(dto);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        userRepository.save(user);
        String code = generateKey();
        cacheService.addValueExpired(AUTH_KEY + user.getEmail(), code, ONE_OUR);
        emailSenderService.sendAuthenticationCode(user.getEmail(), user.getName(), code);

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
                .orElseThrow(() -> new java.util.NoSuchElementException("Can't find user by id: " + id));

        return userMapper.toDto(user);
    }


    @Override
    public UserDto updateUserById(UserDto dto) {

        if (isNull(dto.getId())) {
            throw new ValidationException("User id can't be null");
        }
        userRepository.findById(dto.getId())
                .orElseThrow(() -> new java.util.NoSuchElementException("Can't find user by id: " + dto.getId()));
        User user = userMapper.toUser(dto);
        userRepository.save(user);
        return dto;
    }

    @Override
    public boolean setAdmin(Integer id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new java.util.NoSuchElementException("Can't find user by id: " + id));
        if (!user.isEnable()) {
            throw new ValidationException("User with id" + id + "is not enable");
        }
        user.setRole(RoleEnum.valueOf("ADMIN"));
        userRepository.save(user);
        return true;
    }

    @Override
    public List<UserDto> allUsersList() {

        List<User> users = userRepository.findAll();
        return userMapper.toDtoList(users);

    }

    @Override
    public void deleteById(Integer id) {

        userRepository.deleteById(id);
    }

    @Override
    public void changeExpirationDate(User user, LocalDate newExpirationDate) {

        LocalDate expirationDate = LocalDate.now();
        user.setExpirationDate(expirationDate); //should it be newExpirationDate?
        userRepository.save(user);

    }

    @Override
    public boolean checkExpirationCode(String email, String code) {

        String keyFromDb = cacheService.getValueByKey(AUTH_KEY + email);
        if (isNull(keyFromDb)) {
            throw new ValidationException("Can't find user code by email " + email);
        }
        if (!code.equals(keyFromDb)) {
            throw new ValidationException("This code is wrong!" + code);
        }
        userRepository.setEnable(email);
        return true;
    }


    @Override
    public String generateKey() {

        Random random = new Random();
        return String.valueOf(Math.abs(random.nextInt(9999)));
    }


}
