package com.example.projectcalctool.model;

public class Task {
    private Long taskId;
    private String name;
    private Long projectId;

    public Task() {}

    public Task(Long taskId, String name, Long projectId) {
        this.taskId = taskId;
        this.name = name;
        this.projectId = projectId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
}
