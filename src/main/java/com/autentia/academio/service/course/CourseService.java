package com.autentia.academio.service.course;

import java.io.InputStream;
import java.util.List;

import com.autentia.academio.domain.Course;
import com.autentia.academio.exceptions.NotGeneratedPathException;
import com.autentia.academio.exceptions.NotStoredException;

public interface CourseService {

    List<Course> getAll();

    void createNew(Course course, InputStream inputStream, String fileName) throws NotGeneratedPathException, NotStoredException;
}