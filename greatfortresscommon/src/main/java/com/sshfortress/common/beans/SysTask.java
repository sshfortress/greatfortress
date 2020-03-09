package com.sshfortress.common.beans;


public class SysTask {
    private Long id;

    private String taskType;

    private String creator;

    private String taskBegtime;

    private String taskEndtime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getTaskBegtime() {
        return taskBegtime;
    }

    public void setTaskBegtime(String taskBegtime) {
        this.taskBegtime = taskBegtime;
    }

    public String getTaskEndtime() {
        return taskEndtime;
    }

    public void setTaskEndtime(String taskEndtime) {
        this.taskEndtime = taskEndtime;
    }
}