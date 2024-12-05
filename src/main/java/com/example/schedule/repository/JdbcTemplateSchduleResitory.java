package com.example.schedule.repository;

import com.example.schedule.dto.ScheduleResponseDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcTemplateSchduleResitory implements ScheduleRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateSchduleResitory() {
        this.jdbcTemplate = new JdbcTemplate();
    }
    @Override
    public List<ScheduleResponseDto> findAll() {
        return List.of();
    }
}
