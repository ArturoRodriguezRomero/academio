package com.autentia.academio.web;

import com.autentia.academio.domain.Teacher;
import com.autentia.academio.service.teacher.TeacherService;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TeacherBeanTest {

    private TeacherService teacherService = mock(TeacherService.class);
    private TeacherBean sut = new TeacherBean();

    @Before
    public void init() {
        sut.setTeacherService(teacherService);
    }

    @Test
    public void whenGetAllShouldCallTeacherService() {
        List<Teacher> teachers = new ArrayList<>();
        teachers.add(new Teacher(1, "one"));
        teachers.add(new Teacher(2, "two"));
        teachers.add(new Teacher(5, "five"));
        doReturn(teachers).when(teacherService).getAll();

        List<Teacher> result = sut.getAll();

        verify(teacherService).getAll();
        assertThat(result, equalTo(teachers));
    }

    @Test
    public void WhenGetByIdShouldCallTeacherService() {
        Teacher courseLevel = mock(Teacher.class);
        when(teacherService.getById(anyInt()))
                .thenReturn(courseLevel);

        Teacher result = sut.getById(5);

        verify(teacherService).getById(anyInt());
        assertThat(result, equalTo(courseLevel));
    }
}