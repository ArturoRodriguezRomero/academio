package com.autentia.academio.mapper;

import java.util.List;

import com.autentia.academio.domain.Course;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CourseMapper {

    List<Course> getAll();

    void createNew(Course course);
}