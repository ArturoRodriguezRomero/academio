package com.autentia.academio.mapper;

import com.autentia.academio.domain.Teacher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContextTest.xml")
public class TeacherMapperIT {

    @Autowired
    private TeacherMapper sut;

    @Test
    public void whenGetTeachersAllShouldNotBeEmpty(){
        List<Teacher> all = sut.getAll();

        assertFalse(all.isEmpty());
    }

    @Test
    public void whenGetTeacherByIdShouldReturnATeacher(){
        Teacher expected = new Teacher();
        expected.setId(1);
        expected.setName("test");

        Teacher result = sut.getById(1);

        assertThat(result.getId(), equalTo(expected.getId()));
    }
}