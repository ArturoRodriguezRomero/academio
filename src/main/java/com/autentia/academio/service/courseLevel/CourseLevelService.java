package com.autentia.academio.service.courseLevel;

import java.util.List;

import com.autentia.academio.domain.CourseLevel;


public interface CourseLevelService {

    List<CourseLevel> getAll();

    CourseLevel getById(int id);
}