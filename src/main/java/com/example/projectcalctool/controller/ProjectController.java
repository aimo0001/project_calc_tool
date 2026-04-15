package com.example.projectcalctool.controller;

import com.example.projectcalctool.model.Project;
import com.example.projectcalctool.model.SubTask;
import com.example.projectcalctool.model.Task;
import com.example.projectcalctool.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/projects")
    public String getProjects(Model model) {
        model.addAttribute("projects", projectService.getAllProjects());
        return "projects";
    }

    @GetMapping("/projects/create")
    public String showCreateProjectForm(Model model) {
        model.addAttribute("project", new Project());
        return "create-project";
    }

    @PostMapping("/projects/create")
    public String createProject(@ModelAttribute Project project) {
        projectService.createProject(project);
        return "redirect:/projects";
    }

    @GetMapping("/projects/{projectId}/edit")
    public String showEditProjectForm(@PathVariable Long projectId, Model model) {
        Project project = projectService.getProjectById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));

        model.addAttribute("project", project);
        return "edit-project";
    }

    @PostMapping("/projects/{projectId}/edit")
    public String updateProject(@PathVariable Long projectId, @ModelAttribute Project project) {
        project.setProjectId(projectId);
        projectService.updateProject(project);
        return "redirect:/projects/" + projectId;
    }

    @GetMapping("/projects/{projectId}")
    public String getProjectDetails(@PathVariable Long projectId, Model model) {
        Project project = projectService.getProjectById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));
        List<Task> tasks = projectService.getTasksByProjectId(projectId);
        Map<Long, List<SubTask>> subtasksByTask = new LinkedHashMap<>();

        for (Task currentTask : tasks) {
            subtasksByTask.put(currentTask.getTaskId(), projectService.getSubtasksByTaskId(currentTask.getTaskId()));
        }

        model.addAttribute("project", project);
        model.addAttribute("task", new Task());
        model.addAttribute("subtask", new SubTask());
        model.addAttribute("tasks", tasks);
        model.addAttribute("subtasksByTask", subtasksByTask);
        model.addAttribute("totalHours", projectService.calculateTotalHoursForProject(projectId));
        return "project-details";
    }

    @PostMapping("/projects/{projectId}/tasks/create")
    public String createTask(@PathVariable Long projectId, @ModelAttribute Task task) {
        task.setProjectId(projectId);
        projectService.createTask(task);
        return "redirect:/projects/" + projectId;
    }

    @PostMapping("/projects/{projectId}/tasks/{taskId}/update")
    public String updateTask(@PathVariable Long projectId, @PathVariable Long taskId, @RequestParam String name) {
        Task task = new Task();
        task.setTaskId(taskId);
        task.setProjectId(projectId);
        task.setName(name);
        projectService.updateTask(task);
        return "redirect:/projects/" + projectId;
    }

    @PostMapping("/projects/{projectId}/tasks/{taskId}/delete")
    public String deleteTask(@PathVariable Long projectId, @PathVariable Long taskId) {
        projectService.deleteTask(taskId);
        return "redirect:/projects/" + projectId;
    }

    @PostMapping("/projects/{projectId}/tasks/{taskId}/subtasks/create")
    public String createSubtask(@PathVariable Long projectId, @PathVariable Long taskId, @ModelAttribute SubTask subtask) {
        subtask.setTaskId(taskId);
        projectService.createSubtask(subtask);
        return "redirect:/projects/" + projectId;
    }

    @PostMapping("/projects/{projectId}/tasks/{taskId}/subtasks/{subtaskId}/update")
    public String updateSubtask(@PathVariable Long projectId,
                                @PathVariable Long taskId,
                                @PathVariable Long subtaskId,
                                @RequestParam String name,
                                @RequestParam double hours) {
        SubTask subtask = new SubTask();
        subtask.setSubtaskId(subtaskId);
        subtask.setTaskId(taskId);
        subtask.setName(name);
        subtask.setHours(hours);
        projectService.updateSubtask(subtask);
        return "redirect:/projects/" + projectId;
    }

    @PostMapping("/projects/{projectId}/tasks/{taskId}/subtasks/{subtaskId}/delete")
    public String deleteSubtask(@PathVariable Long projectId,
                                @PathVariable Long taskId,
                                @PathVariable Long subtaskId) {
        projectService.deleteSubtask(subtaskId);
        return "redirect:/projects/" + projectId;
    }

    @PostMapping("/projects/{projectId}/delete")
    public String deleteProject(@PathVariable Long projectId) {
        projectService.deleteProject(projectId);
        return "redirect:/projects";
    }

    @GetMapping("/test")
    public String testConnection(Model model) {
        model.addAttribute("projectsCount", projectService.getAllProjects().size());
        return "test";
    }
}
