package pers.joy.service;

import pers.joy.entity.Course;

import java.util.List;

public interface CourseService {

    /**
     * search courses by course code
     * @param courseCode course code
     * @return list of courses beginning with courseCode
     */
    List<Course> searchByCode(String courseCode);

    /**
     * get selected courses information
     * @param sNo student number
     * @return list of selected courses
     */
    List<Course> getSelectedCoursesInfo(String sNo);

    /**
     * get course name list by teacher No
     * @param tNo teacher No
     * @return list of String
     */
    List<Object> getCourseNameList(String tNo);

    /**
     * get course list
     * @return list of Course objects
     */
    List<Course> getCourseList();

    /**
     * get all course name list
     * @return list of String
     */
    List<Object> getAllCourseNameList();

    /**
     * create course
     * @param course Course object (cCode is not null)
     * @return 1 or -1
     */
    int createCourse(Course course);

    /**
     * delete course-teacher pair
     * @param course Course object
     * @return 1 or -1
     */
    int deleteCourse(Course course);

    /**
     * edit course information
     * @param course Course object
     * @return >0 or -1
     */
    int editCourse(Course course);

    /**
     * add teachers for one course
     * @param courseCode course code
     * @param teacherList list of teacher No
     * @return list of teacher No failed adding
     */
    List<String> addTeacher(String courseCode, List<String> teacherList);
}
