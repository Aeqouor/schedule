package com.example.schedule.entity;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Schedule {
    private Long id;
    private String task;
    private String content;
    private String name;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public Schedule(Long id, String task, String content, String name, LocalDateTime createAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.task = task;
        this.content = content;
        this.name = name;
        this.createdAt = createAt;
        this.modifiedAt = modifiedAt;
    }
}
