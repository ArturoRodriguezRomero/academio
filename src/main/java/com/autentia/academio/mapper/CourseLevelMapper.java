package com.autentia.academio.mapper;

import java.util.List;

import com.autentia.academio.domain.CourseLevel;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CourseLevelMapper {

    List<CourseLevel> getAll();

    CourseLevel getById(int id);
}