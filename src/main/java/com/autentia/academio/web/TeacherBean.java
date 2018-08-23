package com.autentia.academio.web;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.autentia.academio.domain.Teacher;
import com.autentia.academio.service.teacher.TeacherService;

@ManagedBean
@SessionScoped
public class TeacherBean implements Serializable {

    private static final long serialVersionUID = 6902600332275621105L;

    @ManagedProperty("#{teacherService}")
    private transient TeacherService teacherService;

    public List<Teacher> getAll() {
        return teacherService.getAll();
    }

    public Teacher getById(int id) {
        return teacherService.getById(id);
    }

    public TeacherService getTeacherService() {
        return teacherService;
    }

    public void setTeacherService(TeacherService teacherService) {
        this.teacherService = teacherService;
    }
}