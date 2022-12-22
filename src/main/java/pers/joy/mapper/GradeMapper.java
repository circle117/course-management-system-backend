package pers.joy.mapper;

import org.apache.ibatis.annotations.Param;
import pers.joy.entity.Grade;
import pers.joy.entity.User;

import java.util.List;

public interface GradeMapper {

    /**
     * query students who completed certain course
     */
    // administrator
    List<Grade> queryCompletedCourseStudentByCName(String courseName);

    // teacher
    List<Grade> queryCompletedCourseStudentByCNameAndTeacher(@Param("courseName") String courseName,
                                                             @Param("teacherNo") String teacherNo);

    /**
     * query students who are currently taking the course
     */
    // administrator
    List<User> querySelectedCourseStudentByCName(String courseName);

    // teacher
    List<User> querySelectedCourseStudentByCNameAndTeacher(@Param("courseName") String courseName,
                                                           @Param("teacherNo") String teacherNo);

    /**
     * query if a teacher is teaching courses (delete teacher)
     */
    Grade queryGradeByTNo(String teacherNo);

    /**
     * query if a student has taken the certain course (select courses)
     */
    Grade queryGradeByCodeAndStudentNo(Grade grade);

    /**
     * query completed courses by student no
     */
    List<Grade> queryCompletedCoursesByStudentNo(String studentNo);

    /**
     * query courses a student is taking
     */
    String queryGradeSumByStudentNo(String studentNo);

    /**
     * insert grade
     */
    int insertGrade(Grade grade);

    /**
     * input grade and point
     */
    int updateGrade(@Param("studentNo") String studentNo, @Param("courseCode") String courseCode,
                    @Param("grade") String grade, @Param("point") String point);

    /**
     * delete grade (drop course)
     */
    int deleteGrade(Grade grade);
}
