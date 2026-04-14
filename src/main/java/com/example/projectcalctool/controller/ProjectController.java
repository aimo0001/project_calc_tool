package com.example.projectcalctool.controller;

import com.example.projectcalctool.model.Project;
import com.example.projectcalctool.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/test")
    public String testConnection(Model model) {
        model.addAttribute("projectsCount", projectService.getAllProjects().size());
        return "test";
    }
}
