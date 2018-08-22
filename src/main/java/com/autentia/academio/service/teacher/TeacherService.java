package com.autentia.academio.service.teacher;

import java.util.List;

import com.autentia.academio.domain.Teacher;

public interface TeacherService {

    List<Teacher> getAll();

    Teacher getById(int id);
}