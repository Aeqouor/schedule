package com.example.schedule.service;

import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;
import com.example.schedule.repository.ScheduleRepository;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;

    }
    @Override
    public List<ScheduleResponseDto> findAll(String name, String modifiedDate) {
        return scheduleRepository.findAll(name, modifiedDate);
    }

    @Override
    public ScheduleResponseDto findScheduleById(Long id) {
       Schedule schedule = ScheduleRepository.findScheduleByIdOrElseThrow(id);
        return new ScheduleResponseDto(schedule);
    }
}
