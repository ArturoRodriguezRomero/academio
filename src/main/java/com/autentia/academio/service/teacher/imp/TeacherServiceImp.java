package com.autentia.academio.service.teacher.imp;

import java.io.Serializable;
import java.util.List;

import com.autentia.academio.domain.Teacher;
import com.autentia.academio.mapper.TeacherMapper;
import com.autentia.academio.service.teacher.TeacherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("teacherService")
public class TeacherServiceImp implements TeacherService, Serializable {

    @Autowired
    private final transient TeacherMapper teacherMapper;

    public TeacherServiceImp(TeacherMapper teacherMapper) {
        this.teacherMapper = teacherMapper;
    }

    public List<Teacher> getAll() {
        return teacherMapper.getAll();
    }

    public Teacher getById(int id) {
        return teacherMapper.getById(id);
    }

}