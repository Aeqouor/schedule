package com.example.schedule.repository;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcTemplateScheduleRepository implements ScheduleRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateScheduleRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Schedule findScheduleByIdOrElseThrow(Long id) {
        return null;
    }

    @Override
    public List<ScheduleResponseDto> findAll(String name, String modifiedDate) {
        List<Object> params = new ArrayList<>();

        StringBuilder query = new StringBuilder("SELECT * FROM schedule WHERE 1=1");

        if (name != null) {
            query.append(" AND name = ?");
            params.add(name);
        }

        if (modifiedDate != null) {
            query.append(" AND DATE_FORMAT(modified_at,'%Y-%m-%d') = ? ");
            params.add(modifiedDate);
        }

        query.append(" ORDER BY MODIFIED_AT DESC");

        return jdbcTemplate.query(query.toString(), params.toArray(), scheduleRowMapper());
    }

    @Override
    public Schedule findScheduleByIdOrElseThrom(Long id) {
        List<Schedule> result = jdbcTemplate.query("SELECT * FROM schedule WHERE id = ?", scheduleRowMapperV2(), id);
        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, id + ":아이디에 맞는 데이터가 없습니다."));
    }


    private RowMapper<ScheduleResponseDto> scheduleRowMapper() {
        return new RowMapper<ScheduleResponseDto>() {

            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ScheduleResponseDto(
                        rs.getLong("id"),
                        rs.getString("task"),
                        rs.getString("content"),
                        rs.getString("name"),
                        rs.getObject("created_at", LocalDateTime.class),
                        rs.getObject("modified_at", LocalDateTime.class)
                );
            }
        };
    }
    private RowMapper<Schedule> scheduleRowMapperV2() {
        return new RowMapper<Schedule>() {

            @Override
            public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Schedule(
                        rs.getLong("id"),
                        rs.getString("task"),
                        rs.getString("content"),
                        rs.getString("name"),
                        rs.getObject("created_at", LocalDateTime.class),
                        rs.getObject("modified_at", LocalDateTime.class)

                );
            }
        };

    }
}
