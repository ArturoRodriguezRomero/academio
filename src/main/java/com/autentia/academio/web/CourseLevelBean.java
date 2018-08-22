package com.autentia.academio.web;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.autentia.academio.domain.CourseLevel;
import com.autentia.academio.service.courseLevel.CourseLevelService;

/**
 * Course Level Bean Class.
 */
@ManagedBean
@SessionScoped
public class CourseLevelBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManagedProperty("#{courseLevelService}")
    private transient CourseLevelService courseLevelService;

    public List<CourseLevel> getAll() {
        return courseLevelService.getAll();
    }

    public CourseLevel getById(int id) {
        return courseLevelService.getById(id);
    }

    public CourseLevelService getCourseLevelService() {
        return courseLevelService;
    }

    public void setCourseLevelService(CourseLevelService courseLevelService) {
        this.courseLevelService = courseLevelService;
    }
}