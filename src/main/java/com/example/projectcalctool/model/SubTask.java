package com.example.projectcalctool.model;

public class SubTask {
    private Long subtaskId;
    private String name;
    private double hours;
    private Long taskId;

    public SubTask() {}

    public SubTask(Long subtaskId, String name, double hours, Long taskId) {
        this.subtaskId = subtaskId;
        this.name = name;
        this.hours = hours;
        this.taskId = taskId;
    }

    public Long getSubtaskId() {
        return subtaskId;
    }

    public void setSubtaskId(Long subtaskId) {
        this.subtaskId = subtaskId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
}
