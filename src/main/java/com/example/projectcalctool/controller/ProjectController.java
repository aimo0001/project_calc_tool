package com.example.projectcalctool.controller;

import com.example.projectcalctool.model.Project;
import com.example.projectcalctool.model.Task;
import com.example.projectcalctool.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

        model.addAttribute("project", project);
        model.addAttribute("task", new Task());
        model.addAttribute("tasks", projectService.getTasksByProjectId(projectId));
        model.addAttribute("totalHours", projectService.calculateTotalHoursForProject(projectId));
        return "project-details";
    }

    @PostMapping("/projects/{projectId}/tasks/create")
    public String createTask(@PathVariable Long projectId, @ModelAttribute Task task) {
        task.setProjectId(projectId);
        projectService.createTask(task);
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
