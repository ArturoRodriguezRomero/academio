package com.autentia.academio.web;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import com.autentia.academio.domain.Course;
import com.autentia.academio.exceptions.NotFoundException;
import com.autentia.academio.exceptions.NotGeneratedPathException;
import com.autentia.academio.exceptions.NotStoredException;
import com.autentia.academio.service.course.CourseService;
import com.autentia.academio.service.storage.StorageService;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.model.UploadedFile;

@ManagedBean
@ViewScoped
public class CourseBean implements Serializable {

    private transient Logger logger;
    private static final long serialVersionUID = 1L;

    @ManagedProperty("#{courseService}")
    private transient CourseService courseService;
    @ManagedProperty("#{storageService}")
    private transient StorageService storageService;

    private transient List<Course> courses;
    private transient Course newCourse;
    private transient UploadedFile agendaFile;

    @PostConstruct
    public void initialize() {
        courses = getAll();
        newCourse = new Course();
        logger = Logger.getLogger(this.getClass().getName());
    }

    public List<Course> getAll() {
        return courseService.getAll();
    }

    public void createNew() throws IOException {
        try {
            courseService.createNew(newCourse, agendaFile.getInputstream(), agendaFile.getFileName());
            returnToIndex();
        } catch (NotGeneratedPathException e) {
            logger.severe("NotGeneratedPathException");
        } catch (NotStoredException e) {
            logger.severe("NotStoredException");
        }
    }

    public void clientDownload(String filePath) throws IOException {

        FileInputStream fileInputStream;
        try {
            fileInputStream = storageService.clientDownload(filePath);
        } catch (NotFoundException e) {
            logger.severe("NotFoundException");
            return;
        }

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();

        externalContext.responseReset();
        externalContext.setResponseContentType("application/pdf");
        externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"" + FilenameUtils.getName(filePath) + "\"");

        OutputStream output = externalContext.getResponseOutputStream();
        IOUtils.copy(fileInputStream, output);

        facesContext.responseComplete();
    }

    public void returnToIndex() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
    }

    public CourseService getCourseService() {
        return courseService;
    }

    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public Course getNewCourse() {
        return this.newCourse;
    }

    public void setNewCourse(Course newCourse) {
        this.newCourse = newCourse;
    }

    public UploadedFile getAgendaFile() {
        return this.agendaFile;
    }

    public void setAgendaFile(UploadedFile agendaFile) {
        this.agendaFile = agendaFile;
    }

    public StorageService getStorageService(){
        return this.storageService;
    }

    public void setStorageService(StorageService storageService) {
        this.storageService = storageService;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
}