package pers.joy.dao;

import pers.joy.entity.Course;
import pers.joy.entity.SelectCourse;

import java.util.List;

public interface GradeDao {
    /**
     * insert courses in grade table
     * @param selectCourseList include sNo, cCode, tNo
     * @return failed inserted courses
     */
    List<String> insertSelectCourse(List<SelectCourse> selectCourseList);

    /**
     * delete courses in grade table
     * @param selectCourseList include sNo, cCode, tNo
     */
    void deleteCourse(List<SelectCourse> selectCourseList);

    /**
     * query for completed courses (have grade)
     * @param sNo student number
     * @return list of courses with grade and point
     */
    List<SelectCourse> queryForCompletedCourses(String sNo);
}
