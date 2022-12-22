package pers.joy.service;

import pers.joy.entity.Grade;
import pers.joy.entity.User;

import java.util.List;

public interface GradeService {

    /**
     * select course
     * @param gradeList List of Grade object
     * @return List of failed selected course
     */
    List<String> selectCourse(List<Grade> gradeList);

    /**
     * drop courses
     * @param gradeList list of couses wanted to drop
     */
    int dropCourse(List<Grade> gradeList);

    /**
     * get completed courses
     * @param sNo student number
     * @return list of completed courses
     */
    List<Grade> getCompletedCourses(String sNo);

    /**
     * get students and grades who completed the course by course name
     * @param cName course name
     * @return list of Grade objects
     */
    List<Grade> getCompletedCourseStudent(String cName);

    /**
     * get students and grades who completed the course by course name
     * @param cName course name
     * @param tNo teacher No
     * @return list of Grade object
     */
    List<Grade> getCompletedCourseStudent(String cName, String tNo);


    /**
     * get students who are taking the course by course name
     * @param cName course name
     * @return list of User objects
     */
    List<User> getSelectedCourseStudent(String cName);

    /**
     * get students who are taking the course by course name
     * @param cName course name
     * @param tNo teacher No
     * @return list of User object
     */
    List<User> getSelectedCourseStudent(String cName, String tNo);

    /**
     * submit grade
     * @param sNo student No
     * @param cName course name
     * @param grade grade[0, 100]
     * @return 1 or -1
     */
    int submitGrade(String sNo, String cName, String grade);

    /**
     * get number of courses a student is taking
     * @param sNo student number
     * @return the number
     */
    String getSelectedCourseSum(String sNo);
}
