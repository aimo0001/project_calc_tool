package com.example.projectcalctool.service;

import org.springframework.stereotype.Service;
import com.example.projectcalctool.model.Project;
import com.example.projectcalctool.model.SubTask;
import com.example.projectcalctool.model.Task;
import com.example.projectcalctool.repository.ProjectRepository;
import com.example.projectcalctool.repository.SubTaskRepository;
import com.example.projectcalctool.repository.TaskRepository;


import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

        private final ProjectRepository projectRepository;
        private final TaskRepository taskRepository;
        private final SubTaskRepository subtaskRepository;

        public ProjectService(ProjectRepository projectRepository,
                              TaskRepository taskRepository,
                              SubTaskRepository subtaskRepository) {
            this.projectRepository = projectRepository;
            this.taskRepository = taskRepository;
            this.subtaskRepository = subtaskRepository;
        }

        public List<Project> getAllProjects() {
            return projectRepository.findAll();
        }

        public Optional<Project> getProjectById(Long projectId) {
            return projectRepository.findById(projectId);
        }

        public void createProject(Project project) {
            projectRepository.save(project);
        }

        public void updateProject(Project project) {
            projectRepository.update(project);
        }

        public void deleteProject(Long projectId) {
            projectRepository.deleteById(projectId);
        }

        public List<Task> getTasksByProjectId(Long projectId) {
            return taskRepository.findByProjectId(projectId);
        }

        public void createTask(Task task) {
            taskRepository.save(task);
        }

        public void updateTask(Task task) {
            taskRepository.update(task);
        }

        public void deleteTask(Long taskId) {
            taskRepository.deleteById(taskId);
        }

        public List<SubTask> getSubtasksByTaskId(Long taskId) {
            return subtaskRepository.findByTaskId(taskId);
        }

        public void createSubtask(SubTask subtask) {
            subtaskRepository.save(subtask);
        }

        public void updateSubtask(SubTask subtask) {
            subtaskRepository.update(subtask);
        }

        public void deleteSubtask(Long subtaskId) {
            subtaskRepository.deleteById(subtaskId);
        }

        public double calculateTotalHoursForProject(Long projectId) {
            List<Task> tasks = taskRepository.findByProjectId(projectId);
            double totalHours = 0.0;

            for (Task task : tasks) {
                List<SubTask> subtasks = subtaskRepository.findByTaskId(task.getTaskId());
                for (SubTask subtask : subtasks) {
                    totalHours += subtask.getHours();
                }
            }

            return totalHours;
        }
    }

