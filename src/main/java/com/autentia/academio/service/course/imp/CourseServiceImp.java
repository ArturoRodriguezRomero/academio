package com.autentia.academio.service.course.imp;

import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Path;
import java.util.List;

import com.autentia.academio.domain.Course;
import com.autentia.academio.mapper.CourseMapper;
import com.autentia.academio.service.course.CourseService;

import com.autentia.academio.service.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("courseService")
public class CourseServiceImp implements CourseService, Serializable {

    private final transient CourseMapper courseMapper;
    private final transient StorageService storageService;

    @Autowired
    public CourseServiceImp(CourseMapper courseMapper, StorageService storageService) {
        this.courseMapper = courseMapper;
        this.storageService = storageService;
    }

    public List<Course> getAll() {
        return courseMapper.getAll();
    }

    @Override
    public void createNew(Course course, InputStream inputStream, String fileName) {
        Path path = storageService.generateStorePath(fileName);
        course.setAgendaFileName(path.toString());
        storageService.store(inputStream, path);
        courseMapper.createNew(course);
    }
}