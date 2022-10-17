package pers.joy.service;

import pers.joy.entity.Course;
import pers.joy.entity.SelectCourse;

import java.util.List;

public interface StudentService extends UserService{

    List<Course> searchByCode(String courseCode);
    /**
     * get selected courses
     * @param sNo student number
     * @return list of selected courses
     */
    List<Course> getSelectedCourses(String sNo);

    /**
     * select courses
     * @param sNo student number
     * @param courseList courses wanted to select
     * @return failed selected courses
     */
    List<String> selectCourse(String sNo, List<Course> courseList);

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
    List<SelectCourse> getCompletedCourses(String sNo);

    float getGPA(List<SelectCourse> selectCourseList);
}
