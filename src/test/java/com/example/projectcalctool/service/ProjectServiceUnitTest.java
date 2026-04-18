package com.example.projectcalctool.service;

import com.example.projectcalctool.model.SubTask;
import com.example.projectcalctool.model.Task;
import com.example.projectcalctool.repository.ProjectRepository;
import com.example.projectcalctool.repository.SubTaskRepository;
import com.example.projectcalctool.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ProjectServiceUnitTest {

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private SubTaskRepository subTaskRepository;

    private ProjectService projectService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        projectService = new ProjectService(projectRepository, taskRepository, subTaskRepository);
    }

    @Test
    void calculateTotalHoursForProjectReturnsSumOfAllSubtaskHours() {
        Task task1 = new Task(1L, "Design", 1L);
        Task task2 = new Task(2L, "Backend", 1L);

        when(taskRepository.findByProjectId(1L)).thenReturn(List.of(task1, task2));
        when(subTaskRepository.findByTaskId(1L)).thenReturn(List.of(
                new SubTask(1L, "Wireframe", 2.0, 1L),
                new SubTask(2L, "ER-diagram", 3.0, 1L)
        ));
        when(subTaskRepository.findByTaskId(2L)).thenReturn(List.of(
                new SubTask(3L, "Repository", 4.0, 2L),
                new SubTask(4L, "Service", 1.0, 2L)
        ));

        double totalHours = projectService.calculateTotalHoursForProject(1L);

        assertEquals(10.0, totalHours);
    }
}
