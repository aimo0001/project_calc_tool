package com.example.projectcalctool.controller;

import com.example.projectcalctool.service.ProjectService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {this.projectService = projectService;}

    @GetMapping("/test")
    public String testConnection() {return "Antal projekter: " + projectService.getAllProjects().size();}
}
