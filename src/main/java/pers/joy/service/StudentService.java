package pers.joy.service;

import pers.joy.entity.Course;
import pers.joy.entity.Grade;
import pers.joy.entity.User;

import java.util.List;

public interface StudentService{

    User signIn(User user);

    List<Course> searchByCode(String courseCode);
    /**
     * get selected courses
     * @param sNo student number
     * @return list of selected courses
     */
    List<Course> getSelectedCourses(String sNo);

    /**
     * select course
     * @param gradeList List of Grade object
     * @return List of failed selected course
     */
    List<String> selectCourse(List<Grade> gradeList);

    /**
     * drop courses
     * @param sNo student number
     * @param courseList list of couses wanted to drop
     */
    void dropCourse(String sNo, List<Course> courseList);

    /**
     * get completed courses
     * @param sNo student number
     * @return list of completed courses
     */
    List<Grade> getCompletedCourses(String sNo);

    float getGPA(List<Grade> gradeList);
}
