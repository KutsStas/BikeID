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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
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

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new NoSuchElementException("Can't find User by ID " + dto.getUserId()));
        Workshop workshop = workshopMapper.toWorkshop(dto);
        workshop.setUser(user);
        workshopRepository.save(workshop);
        return workshop.getId();
    }

    @Override
    public WorkshopDto getWorkshopById(Integer id) {

        Workshop workshop = workshopRepository.
                findById(id).orElseThrow(() -> new NoSuchElementException("Can't find Workshop by id" + id));
        return workshopMapper.toDto(workshop);

    }

    @Override
    public WorkshopDto updateWorkshopInfo(WorkshopDto dto) {

        if (isNull(dto.getId())) {
            throw new ValidationException("id can't be null");
        }
        workshopRepository.findById(dto.getId())
                .orElseThrow(() -> new NoSuchElementException("Can't find Workshop by id: " + dto.getId()));
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new NoSuchElementException("Can't find User by ID " + dto.getUserId()));
        Workshop workshop = workshopMapper.toWorkshop(dto);
        workshop.setUser(user);
        workshopRepository.save(workshop);
        return dto;
    }

    @Override
    public List<WorkshopDto> allWorkshopList() {

        User user = (User) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        List<Workshop> workshops = workshopRepository.findAllByUser(user);
        return workshops.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {

        workshopRepository.deleteById(id);

    }

    private WorkshopDto mapToDto(Workshop workshop) {

        return workshopMapper.toDto(workshop);
    }


}
