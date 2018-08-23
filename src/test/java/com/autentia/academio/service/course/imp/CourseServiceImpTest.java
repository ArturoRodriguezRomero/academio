package com.autentia.academio.service.course.imp;

import com.autentia.academio.domain.Course;
import com.autentia.academio.exceptions.NotGeneratedPathException;
import com.autentia.academio.exceptions.NotStoredException;
import com.autentia.academio.mapper.CourseMapper;
import com.autentia.academio.service.course.CourseService;
import com.autentia.academio.service.storage.StorageService;
import org.junit.Test;

import java.io.InputStream;
import java.nio.file.Path;

import static org.mockito.Mockito.*;

public class CourseServiceImpTest {

    private final CourseMapper courseMapper = mock(CourseMapper.class);
    private final StorageService storageService = mock(StorageService.class);
    private final CourseService sut = new CourseServiceImp(courseMapper, storageService);

    @Test
    public void whenGetAllCoursesShouldCallMapper() {
        sut.getAll();

        verify(courseMapper).getAll();
    }

    @Test
    public void whenCreateNewCourseShouldCallMapper() throws NotGeneratedPathException, NotStoredException {
        String fileName = "fileName";
        String pathGenerado = "path generado";
        InputStream inputStream = mock(InputStream.class);
        Path path = mock(Path.class);
        doReturn(pathGenerado).when(path).toString();
        Course course = new Course();
        course.setAgendaFileName(pathGenerado);
        doReturn(path).when(storageService).generateStorePath(fileName);

        sut.createNew(course, inputStream, fileName);

        verify(storageService).generateStorePath(fileName);
        verify(storageService).store(inputStream, path);
        verify(courseMapper).createNew(course);
    }
}