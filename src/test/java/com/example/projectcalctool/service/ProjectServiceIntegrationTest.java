package com.example.projectcalctool.service;

import com.example.projectcalctool.model.Project;
import com.example.projectcalctool.model.SubTask;
import com.example.projectcalctool.model.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@ActiveProfiles("test")
class ProjectServiceIntegrationTest {

    @Autowired
    private ProjectService projectService;

    @Test
    void canReadProjectTasksSubtasksAndCalculateTotalHours() {
        List<Project> projects = projectService.getAllProjects();

        assertFalse(projects.isEmpty());

        Project project = projects.getFirst();
        List<Task> tasks = projectService.getTasksByProjectId(project.getProjectId());
        List<SubTask> subtasks = projectService.getSubtasksByTaskId(tasks.getFirst().getTaskId());

        assertEquals(2, tasks.size());
        assertEquals(2, subtasks.size());
        assertEquals(8.0, projectService.calculateTotalHoursForProject(project.getProjectId()));
    }
}
