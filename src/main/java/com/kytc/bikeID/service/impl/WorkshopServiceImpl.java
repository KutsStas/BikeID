package com.kytc.bikeID.service.impl;

import com.kytc.bikeID.dto.WorkshopDto;
import com.kytc.bikeID.entity.User;
import com.kytc.bikeID.entity.Workshop;
import com.kytc.bikeID.exeption.ValidationException;
import com.kytc.bikeID.mapper.WorkshopMapper;
import com.kytc.bikeID.repository.UserRepository;
import com.kytc.bikeID.repository.WorkshopRepository;
import com.kytc.bikeID.service.WorkshopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class WorkshopServiceImpl implements WorkshopService {

    private final UserRepository userRepository;

    private final WorkshopMapper workshopMapper;

    private final WorkshopRepository workshopRepository;

    @Override
    public Integer addWorkshop(WorkshopDto dto) {

        if (isNull(dto.getManagerId())) {
            throw new ValidationException("Manager id can't be null");
        }
        User user = userRepository.findById(dto.getManagerId())
                .orElseThrow(() -> new java.util.NoSuchElementException("Can't find User by ID " + dto.getManagerId()));
        Workshop workshop = workshopMapper.toWorkshop(dto);
        workshop.setManager(user);
        workshopRepository.save(workshop);
        return workshop.getId();
    }

    @Override
    public WorkshopDto getWorkshopById(Integer id) {

        Workshop workshop = workshopRepository.
                findById(id).orElseThrow(() -> new java.util.NoSuchElementException("Can't find Workshop by id" + id));
        return workshopMapper.toDto(workshop);

    }

    @Override
    public WorkshopDto updateWorkshopInfo(WorkshopDto dto) {

        if (isNull(dto.getId())) {
            throw new ValidationException("Workshop id can't be null");
        }
        workshopRepository.findById(dto.getId())
                .orElseThrow(() -> new java.util.NoSuchElementException("Can't find Workshop by id: " + dto.getId()));
        User user = userRepository.findById(dto.getManagerId())
                .orElseThrow(() -> new java.util.NoSuchElementException("Can't find User by ID " + dto.getManagerId()));
        Workshop workshop = workshopMapper.toWorkshop(dto);
        workshop.setManager(user);
        workshopRepository.save(workshop);
        return dto;
    }

    @Override
    public List<WorkshopDto> allWorkshopList() {

        List<Workshop> workshops = workshopRepository.findAll();
        return workshops.stream()
                .map(workshopMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {

        workshopRepository.deleteById(id);

    }

}



