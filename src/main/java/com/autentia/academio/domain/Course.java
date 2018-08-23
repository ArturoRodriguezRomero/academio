package com.autentia.academio.domain;

public class Course {
    private int id;
    private Boolean isActive;
    private String title;
    private int hours;
    private int courseLevel;
    private int teacher;
    private String agendaFileName;

    public int getId() {
        return id;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getTeacher() {
        return teacher;
    }

    public void setTeacher(int teacher) {
        this.teacher = teacher;
    }

    public int getCourseLevel() {
        return courseLevel;
    }

    public void setCourseLevel(int courseLevel) {
        this.courseLevel = courseLevel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAgendaFileName() {
        return this.agendaFileName;
    }

    public void setAgendaFileName(String agendaFileName) {
        this.agendaFileName = agendaFileName;
    }
}