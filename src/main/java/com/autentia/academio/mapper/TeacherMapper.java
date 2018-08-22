package com.autentia.academio.mapper;

import java.util.List;

import com.autentia.academio.domain.Teacher;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TeacherMapper {

    List<Teacher> getAll();

    Teacher getById(int id);
}