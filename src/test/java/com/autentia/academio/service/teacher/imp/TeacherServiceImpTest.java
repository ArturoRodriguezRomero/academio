package com.autentia.academio.service.teacher.imp;


import com.autentia.academio.mapper.TeacherMapper;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TeacherServiceImpTest {

    private final TeacherMapper teacherMapper = mock(TeacherMapper.class);
    private final TeacherServiceImp sut = new TeacherServiceImp(teacherMapper);

    @Test
    public void whenGetAllTeachersShouldCallMapper() {
        sut.getAll();

        verify(teacherMapper).getAll();
    }

    @Test
    public void whenGetByIdShouldCallMapper() {
        sut.getById(1);

        verify(teacherMapper).getById(1);
    }
}