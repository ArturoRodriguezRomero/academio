package com.autentia.academio.web;

import com.autentia.academio.domain.Course;
import com.autentia.academio.exceptions.NotFoundException;
import com.autentia.academio.exceptions.NotGeneratedPathException;
import com.autentia.academio.exceptions.NotStoredException;
import com.autentia.academio.service.course.CourseService;
import com.autentia.academio.service.storage.StorageService;
import org.junit.Before;
import org.junit.Test;
import org.primefaces.model.UploadedFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;

public class CourseBeanTest {

    private CourseBean sut = new CourseBean();
    private CourseService courseService = mock(CourseService.class);
    private StorageService storageService = mock(StorageService.class);
    private Logger logger = mock(Logger.class);
    private List<Course> courses = new ArrayList<>();

    @Before
    public void init(){
        sut.setCourseService(courseService);
        sut.setStorageService(storageService);
        sut.setLogger(logger);
        doReturn(courses).when(courseService).getAll();
    }

    @Test
    public void whenInitializeShouldBeInitialized() {
        sut.setNewCourse(mock(Course.class));

        sut.initialize();

        verify(courseService).getAll();
        assertThat(sut.getNewCourse(), is(notNullValue()));
    }

    @Test
    public void whenGetAllCalledCourseServiceShouldBeCalledAndReturnACourseList(){
        List<Course> courses = sut.getAll();

        verify(courseService).getAll();
        assertThat(courses, is(notNullValue()));
    }

    @Test
    public void whenCreateNewCourseAndNotGeneratedPathExceptionThrowsShouldLogAMessage() throws NotGeneratedPathException, NotStoredException, IOException {
        Course newCourse = mock(Course.class);
        UploadedFile uploadedFile = mock(UploadedFile.class);
        InputStream inputStream = mock(InputStream.class);
        String fileName = "filename";
        doReturn(inputStream).when(uploadedFile).getInputstream();
        doReturn(fileName).when(uploadedFile).getFileName();
        sut.setNewCourse(newCourse);
        sut.setAgendaFile(uploadedFile);
        doThrow(NotGeneratedPathException.class).when(courseService).createNew(newCourse, inputStream, fileName);

        sut.createNew();

        verify(logger).severe("NotGeneratedPathException");
    }

    @Test
    public void whenCreateNewCourseAndNotStoredExceptionThrowsShouldLogAMessage() throws NotGeneratedPathException, NotStoredException, IOException {
        Course newCourse = mock(Course.class);
        UploadedFile uploadedFile = mock(UploadedFile.class);
        InputStream inputStream = mock(InputStream.class);
        String fileName = "filename";
        doReturn(inputStream).when(uploadedFile).getInputstream();
        doReturn(fileName).when(uploadedFile).getFileName();
        sut.setNewCourse(newCourse);
        sut.setAgendaFile(uploadedFile);
        doThrow(NotStoredException.class).when(courseService).createNew(newCourse, inputStream, fileName);

        sut.createNew();

        verify(logger).severe("NotStoredException");
    }

    @Test
    public void whenDownloadAndNotFoundExceptionThrowsShouldLogAMessage() throws NotFoundException, IOException {
        String filePath = "filePath";
        doThrow(NotFoundException.class).when(storageService).clientDownload(filePath);

        sut.clientDownload(filePath);

        verify(logger).severe("NotFoundException");
    }

}