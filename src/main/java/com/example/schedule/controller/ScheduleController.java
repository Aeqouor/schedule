package com.example.schedule.controller;
import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
       this.scheduleService = scheduleService;

    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAll(@RequestParam(value = "name", required = false) String name,
                                                             @RequestParam(value = "modifiedDate", required = false) String modifiedDate) {
        return new ResponseEntity<>(scheduleService.findAll(name, modifiedDate), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findById(@PathVariable Long id) {
        return new ResponseEntity<>(scheduleService.findScheduleById(id), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto scheduleRequestDto) {
        return new ResponseEntity<>(scheduleService.saveScheule(scheduleRequestDto), HttpStatus.CREATED);
    }



}
