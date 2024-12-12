package com.example.schedule.repository;
import com.example.schedule.dto.ScheduleResponseDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class JdbcTemplateSchduleResitory implements ScheduleRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateSchduleResitory(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<ScheduleResponseDto> findAll(String name, String modifiedDate) {
        List<Object> params = new ArreyList<>();

        StringBuilder query = new StringBuilder("SELECT * FROM schedule WHERE 1=1");

        if (name != null) {
            query.append(" AND name = ?");
            params.add(name);
        }

        if (modifiedDate != null) {
            query.append(" AND DATE_FORMAT(modified_at", '%Y-%m-%d') = ? ");
            params.add(modifiedDate);
        }

        query.append(" ORDER BY MODIFIED_AT DESC");

        return jdbcTemplate.query(query.toString(), params.toArray(), scheduleRowMapper());

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
}
