package pers.joy.service;

import pers.joy.entity.Course;

import java.util.List;

public interface CourseService {

    /**
     * search courses by course code
     * @param courseCode course code
     * @return list of courses beginning with courseCode
     */
    List<Course> searchByCode(String courseCode, int pageNum, int pageSize);

    /**
     * get course sum
     * @param courseCode course code
     * @return a String
     */
    String getCourseSumByCCode(String courseCode);

    /**
     * get selected courses information
     * @param sNo student number
     * @param pageNum page number
     * @param pageSize page size
     * @return list of selected courses
     */
    List<Course> getSelectedCoursesInfo(String sNo, int pageNum, int pageSize);

    /**
     * get course name list by teacher No
     * @param tNo teacher No
     * @return list of String
     */
    List<String> getCourseNameList(String tNo);

    /**
     * get course list
     * @return list of Course objects
     */
    List<Course> getCourseList(int pageNum, int pageSize);

    /**
     * get the sum of courses
     * @return a string
     */
    String getCourseSum();

    /**
     * get all course name list
     * @return list of String
     */
    List<String> getAllCourseNameList();

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
