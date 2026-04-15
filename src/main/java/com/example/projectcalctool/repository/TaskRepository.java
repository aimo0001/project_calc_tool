package com.example.projectcalctool.repository;

import com.example.projectcalctool.model.Task;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class TaskRepository {
    private final JdbcTemplate jdbcTemplate;

    public TaskRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Task> findByProjectId(Long projectId) {
        String sql = "SELECT * FROM task WHERE project_id = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Task(
                        rs.getLong("task_id"),
                        rs.getString("name"),
                        rs.getLong("project_id")
                ), projectId
        );
    }

    public void save(Task task) {
        String sql = "INSERT INTO task (name, project_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, task.getName(), task.getProjectId());
    }

    public void update(Task task) {
        String sql = "UPDATE task SET name = ? WHERE task_id = ?";
        jdbcTemplate.update(sql, task.getName(), task.getTaskId());
    }

    public void deleteById(Long taskId) {
        String sql = "DELETE FROM task WHERE task_id = ?";
        jdbcTemplate.update(sql, taskId);
    }
}
