package com.autentia.academio.mapper;

import com.autentia.academio.domain.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContextTest.xml")
public class CourseMapperIT {
    @Autowired private CourseMapper sut;

    @Test
    public void whenGetAllShouldNotBeEmpty(){
        List<Course> all = sut.getAll();

        assertFalse(all.isEmpty());
    }

    @Test
    @Transactional
    public void whenCreateNewCourseShouldBeInserted(){
        Course newCourse = new Course();
        newCourse.setAgendaFileName("test");
        newCourse.setCourseLevel(1);
        newCourse.setHours(1);
        newCourse.setIsActive(true);
        newCourse.setTeacher(1);
        newCourse.setTitle("test");
        List<Course> all = sut.getAll();

        sut.createNew(newCourse);
        List<Course> allAfter = sut.getAll();

        assertTrue(all.size() + 1 == allAfter.size());
    }

}