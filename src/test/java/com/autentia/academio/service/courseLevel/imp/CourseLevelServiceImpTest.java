package com.autentia.academio.service.courseLevel.imp;

import com.autentia.academio.mapper.CourseLevelMapper;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CourseLevelServiceImpTest {

    private final CourseLevelMapper courseLevelMapper = mock(CourseLevelMapper.class);
    private final CourseLevelServiceImp sut = new CourseLevelServiceImp(courseLevelMapper);

    @Test
    public void whenGetAllCoursesShouldCallMapper() {
        sut.getAll();

        verify(courseLevelMapper).getAll();
    }

    @Test
    public void whenGetByIdShouldCallMapper() {
        sut.getById(1);

        verify(courseLevelMapper).getById(1);
    }
}