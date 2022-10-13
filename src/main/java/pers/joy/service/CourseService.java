package pers.joy.service;

import pers.joy.entity.Course;

import java.util.List;

public interface CourseService {
    List<Course> searchByCode(String courseCode);
}
