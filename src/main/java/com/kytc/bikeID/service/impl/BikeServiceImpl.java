package com.kytc.bikeID.service.impl;

import com.kytc.bikeID.dto.BikeDto;
import com.kytc.bikeID.entity.Bike;
import com.kytc.bikeID.entity.User;
import com.kytc.bikeID.entity.Workshop;
import com.kytc.bikeID.exeption.ValidationException;
import com.kytc.bikeID.mapper.BikeMapper;
import com.kytc.bikeID.repository.BikeRepository;
import com.kytc.bikeID.repository.UserRepository;
import com.kytc.bikeID.repository.WorkshopRepository;
import com.kytc.bikeID.service.BikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class BikeServiceImpl implements BikeService {

    private final BikeMapper bikeMapper;

    private final BikeRepository bikeRepository;

    private final UserRepository userRepository;

    private final WorkshopRepository workshopRepository;


    @Override
    public Integer addBike(BikeDto dto) {

        Workshop workshop = workshopRepository.findById(dto.getWorkshopId())
                .orElseThrow(() -> new NoSuchElementException("Can't find workshop by Id" + dto.getWorkshopId()));
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new NoSuchElementException("Can't find User by ID " + dto.getUserId()));
        Bike bike = bikeMapper.toEntity(dto);
        bike.setUser(user);
        bike.setWorkshop(workshop);
        bikeRepository.save(bike);
        return bike.getId();
    }

    @Override
    public BikeDto getBikeById(Integer id) {

        Bike bike = bikeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Can't find bike by id: " + id));

        return bikeMapper.toDto(bike);
    }

    @Override
    public BikeDto updateBikeById(BikeDto dto) {

        if (isNull(dto.getId())) {
            throw new ValidationException("id can't be null");
        }
        bikeRepository.findById(dto.getId())
                .orElseThrow(() -> new NoSuchElementException("Can't find bike by id: " + dto.getId()));
        Workshop workshop = workshopRepository.findById(dto.getWorkshopId())
                .orElseThrow(() -> new NoSuchElementException("Can't find workshop by Id" + dto.getWorkshopId()));
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new NoSuchElementException("Can't find User by ID " + dto.getUserId()));
        Bike bike = bikeMapper.toEntity(dto);
        bike.setUser(user);
        bike.setWorkshop(workshop);
        bikeRepository.save(bike);
        return dto;
    }

    @Override
    public List<BikeDto> allUsersBike() {

        User user = (User) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        List<Bike> bikes = bikeRepository.findAllByUser(user);
        return bikes.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteBikeById(Integer id) {

        bikeRepository.deleteById(id);

    }

    private BikeDto mapToDto(Bike bike) {

        return bikeMapper.toDto(bike);
    }

}
