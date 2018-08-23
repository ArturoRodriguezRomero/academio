package com.autentia.academio.web;

import com.autentia.academio.domain.Course;
import com.autentia.academio.exceptions.NotFoundException;
import com.autentia.academio.exceptions.NotGeneratedPathException;
import com.autentia.academio.exceptions.NotStoredException;
import com.autentia.academio.service.course.CourseService;
import com.autentia.academio.service.storage.StorageService;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.primefaces.model.UploadedFile;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({FacesContext.class, IOUtils.class})
public class CourseBeanIT {

    FacesContext facesContextMock = mock(FacesContext.class);
    ExternalContext externalContextMock = mock(ExternalContext.class);
    IOUtils ioUtils = mock(IOUtils.class);

    private CourseBean sut = new CourseBean();
    private CourseService courseService = mock(CourseService.class);
    private StorageService storageService = mock(StorageService.class);
    private List<Course> courses = new ArrayList<>();

    @Before
    public void init(){
        PowerMockito.mockStatic(FacesContext.class);
        PowerMockito.mockStatic(IOUtils.class);

        when(FacesContext.getCurrentInstance()).thenReturn(facesContextMock);
        when(facesContextMock.getExternalContext()).thenReturn(externalContextMock);

        sut.setCourseService(courseService);
        sut.setStorageService(storageService);
        doReturn(courses).when(courseService).getAll();
    }

    @Test
    public void whenCreateNewShouldCallServiceAndReturnToIndex() throws IOException, NotGeneratedPathException, NotStoredException {
        Course newCourse = mock(Course.class);
        UploadedFile uploadedFile = mock(UploadedFile.class);
        InputStream inputStream = mock(InputStream.class);
        String fileName = "filename";
        doReturn(inputStream).when(uploadedFile).getInputstream();
        doReturn(fileName).when(uploadedFile).getFileName();
        sut.setNewCourse(newCourse);
        sut.setAgendaFile(uploadedFile);

        sut.createNew();

        verify(courseService).createNew(newCourse, inputStream, fileName);
        verify(externalContextMock).redirect("index.xhtml");

    }

    @Test
    public void whenClientDownloadShouldCallStorageService() throws IOException, NotFoundException {
        String filePath = "test.pdf";
        OutputStream outputStream = mock(OutputStream.class);
        FileInputStream fileInputStream = mock(FileInputStream.class);
        doReturn(fileInputStream).when(storageService).clientDownload(filePath);
        doReturn(outputStream).when(externalContextMock).getResponseOutputStream();

        sut.clientDownload(filePath);

        verify(storageService).clientDownload("test.pdf");
        verify(externalContextMock).responseReset();
        verify(externalContextMock).setResponseContentType("application/pdf");
        verify(externalContextMock).setResponseHeader("Content-Disposition", "attachment; filename=\"" + filePath + "\"");

        verify(ioUtils).copy(fileInputStream, outputStream);
    }
}