package com.autentia.academio.service.courseLevel.imp;

import java.io.Serializable;
import java.util.List;

import com.autentia.academio.domain.CourseLevel;
import com.autentia.academio.mapper.CourseLevelMapper;
import com.autentia.academio.service.courseLevel.CourseLevelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("courseLevelService")
public class CourseLevelServiceImp implements CourseLevelService, Serializable {

    @Autowired
    private final transient CourseLevelMapper courseLevelMapper;

    public CourseLevelServiceImp(CourseLevelMapper courseLevelMapper) {
        this.courseLevelMapper = courseLevelMapper;
    }

    public List<CourseLevel> getAll() {
        return courseLevelMapper.getAll();
    }

    public CourseLevel getById(int id) {
        return courseLevelMapper.getById(id);
    }

}