package com.example.schedule.repository;

import com.example.schedule.dto.ScheduleResponseDto;

import java.util.List;

public interface ScheduleRepository {
    List<ScheduleResponseDto> findAll();
}
