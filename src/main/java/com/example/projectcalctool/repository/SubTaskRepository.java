package com.example.projectcalctool.repository;

import com.example.projectcalctool.model.SubTask;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class SubTaskRepository {
    private final JdbcTemplate jdbcTemplate;

    public SubTaskRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<SubTask> findByTaskId(Long taskId) {
        String sql = "SELECT * FROM subtask WHERE task_id = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new SubTask(
                        rs.getLong("subtask_id"),
                        rs.getString("name"),
                        rs.getDouble("hours"),
                        rs.getLong("task_id")
                ), taskId
        );
    }

    public void save(SubTask subtask) {
        String sql = "INSERT INTO subtask (name, hours, task_id) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, subtask.getName(), subtask.getHours(), subtask.getTaskId());
    }

    public void update(SubTask subtask) {
        String sql = "UPDATE subtask SET name = ?, hours = ? WHERE subtask_id = ?";
        jdbcTemplate.update(sql, subtask.getName(), subtask.getHours(), subtask.getSubtaskId());
    }

    public void deleteById(Long subtaskId) {
        String sql = "DELETE FROM subtask WHERE subtask_id = ?";
        jdbcTemplate.update(sql, subtaskId);
    }
}
