package com.example.projectcalctool.repository;

import com.example.projectcalctool.model.Project;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ProjectRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProjectRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Project> findAll() {
        String sql = "SELECT * FROM project";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Project(
                        rs.getLong("project_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDate("start_date") != null ? rs.getDate("start_date").toLocalDate() : null
                )
        );
    }

    public void save(Project project) {
        String sql = "INSERT INTO project (name, description, start_date) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, project.getName(), project.getDescription(), project.getStartDate());
    }
}
