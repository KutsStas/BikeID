package com.kytc.bikeID.service.impl;

import com.kytc.bikeID.dto.BikeDto;
import com.kytc.bikeID.entity.Bike;
import com.kytc.bikeID.entity.User;
import com.kytc.bikeID.entity.Workshop;
import com.kytc.bikeID.entity.enums.LegalStatus;
import com.kytc.bikeID.exception.ValidationException;
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

        dto.setId(null);
        Workshop workshop = getWorkshop(dto);
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new NoSuchElementException("Can't find User by ID " + dto.getUserId()));
        Bike bike = bikeMapper.toEntity(dto);
        bike.setUser(user);
        List<Workshop> workshops = bike.getWorkshops();
        if (!workshops.contains(workshop)) {
            workshops.add(workshop);
        }
        bike.setLegalStatus(LegalStatus.valueOf("LEGAL"));
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
    public String checkBikeLegalStatus(Integer id) {

        Bike bike = bikeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Can't find bike by id: " + id));
        if (isNull(bike.getLegalStatus())) {
            throw new ValidationException("Can't find legal status bike with id: " + id);
        } else {
            return bike.getLegalStatus().toString();
        }
    }

    @Override
    public BikeDto updateBikeById(BikeDto dto) {

        if (isNull(dto.getId())) {
            throw new ValidationException("Id can't be null");
        }
        bikeRepository.findById(dto.getId())
                .orElseThrow(() -> new NoSuchElementException("Can't find bike by id: " + dto.getId()));
        Workshop workshop = getWorkshop(dto);
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new NoSuchElementException("Can't find User by ID " + dto.getUserId()));
        Bike bike = bikeMapper.toEntity(dto);
        bike.setUser(user);
        List<Workshop> workshops = bike.getWorkshops();
        if (!workshops.contains(workshop)) {
            workshops.add(workshop);
        }
        bikeRepository.save(bike);
        return dto;
    }

    @Override
    public List<BikeDto> listOfStolenBikes() {

        List<Bike> bikes = bikeRepository.findAllByRole();
        return bikes.stream()
                .map(bikeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public BikeDto updateBikeLegalStatus(Integer id) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        if (!getBikesId(user).contains(id)) {
            throw new ValidationException("Bike id for this user is wrong " + id);
        }
        Bike bike = bikeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Can't find bike by id: " + id));
        if (bike.getLegalStatus().equals(LegalStatus.LEGAL)) {
            bike.setLegalStatus(LegalStatus.ILLEGAL);
        } else {
            bike.setLegalStatus(LegalStatus.LEGAL);
        }
        bikeRepository.save(bike);
        return bikeMapper.toDto(bike);
    }

    private List<Integer> getBikesId(User user) {

        return user.getBikes().stream().map(Bike::getId).collect(Collectors.toList());
    }

    private Workshop getWorkshop(BikeDto dto) {

        return workshopRepository.findById(dto.getWorkshopId())
                .orElseThrow(() -> new ValidationException("Can't find workshop by Id " + dto.getWorkshopId()));
    }

    @Override
    public List<BikeDto> allUsersBike() {

        User user = (User) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        List<Bike> bikes = bikeRepository.findAllByUser(user);
        return bikes.stream()
                .map(bikeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteBikeById(Integer id) {

        bikeRepository.deleteById(id);

    }


}
