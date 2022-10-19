package pers.joy.dao;

import pers.joy.entity.Grade;
import pers.joy.entity.User;

import java.util.List;

public interface GradeDao {
    /**
     * insert courses in grade table
     * @param gradeList include sNo, cCode, tNo
     * @return failed inserted courses
     */
    List<String> insertSelectCourse(List<Grade> gradeList);

    /**
     * delete courses in grade table
     * @param gradeList include sNo, cCode, tNo
     */
    void deleteCourse(List<Grade> gradeList);

    /**
     * query for completed courses (have grade)
     * @param sNo student number
     * @return list of courses with grade and point
     */
    List<Grade> queryForCompletedCourses(String sNo);

    List<Grade> queryForCompletedCourseStudent(String cName);

    List<User> queryForSelectedCourseStudent(String cName);

    int updateGrade(String sNo, String cCode, String grade, String point);

    List<Grade> queryForCompletedCourseStudentForTeacher(String cName, String tNo);

    List<User> queryForSelectedCourseStudentForTeacher(String cName, String tNo);
}
