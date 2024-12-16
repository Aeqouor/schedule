package com.example.schedule.dto;

import com.example.schedule.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {
    private Long id;
    private String task;
    private String content;
    private String name;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.task = schedule.getTask();
        this.name = schedule.getName();
        this.content = schedule.getContent();
        this.createAt = schedule.getCreatedAt();
        this.modifiedAt = schedule.getModifiedAt();
    }

    public ScheduleResponseDto(Long id, String task, String content, String name, LocalDateTime createAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.task = task;
        this.content = content;
        this.name = name;
        this.createAt = createAt;
        this.modifiedAt = modifiedAt;
    }
}



