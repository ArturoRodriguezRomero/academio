package com.autentia.academio.domain;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id == course.id &&
                hours == course.hours &&
                courseLevel == course.courseLevel &&
                teacher == course.teacher &&
                Objects.equals(isActive, course.isActive) &&
                Objects.equals(title, course.title) &&
                Objects.equals(agendaFileName, course.agendaFileName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isActive, title, hours, courseLevel, teacher, agendaFileName);
    }
}