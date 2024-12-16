package com.example.schedule.repository;

import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;

import java.util.List;

public interface ScheduleRepository {

    static Schedule findScheduleByIdOrElseThrow(Long id);


    List<ScheduleResponseDto> findAll(String name, String modifiedDate);


    Schedule findScheduleByIdOrElseThrom(Long id);
}
