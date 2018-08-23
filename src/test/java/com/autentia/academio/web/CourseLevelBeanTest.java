package com.autentia.academio.web;

import com.autentia.academio.domain.CourseLevel;
import com.autentia.academio.service.courseLevel.CourseLevelService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class CourseLevelBeanTest {

    private CourseLevelService courseLevelService = mock(CourseLevelService.class);
    private CourseLevelBean sut = new CourseLevelBean();

    @Before
    public void init() {
        sut.setCourseLevelService(courseLevelService);
    }

    @Test
    public void whenGetAllShouldCallCourseLevelService() {
        List<CourseLevel> courseLevels = new ArrayList<>();
        courseLevels.add(new CourseLevel(1, "one"));
        courseLevels.add(new CourseLevel(2, "two"));
        courseLevels.add(new CourseLevel(5, "five"));
        doReturn(courseLevels).when(courseLevelService).getAll();

        List<CourseLevel> result = sut.getAll();

        verify(courseLevelService).getAll();
        assertThat(result, equalTo(courseLevels));
    }

    @Test
    public void WhenGetByIdShouldCallCourseLevelService() {
        CourseLevel courseLevel = mock(CourseLevel.class);
        when(courseLevelService.getById(anyInt()))
                .thenReturn(courseLevel);

        CourseLevel result = sut.getById(5);

        verify(courseLevelService).getById(anyInt());
        assertThat(result, equalTo(courseLevel));
    }
}