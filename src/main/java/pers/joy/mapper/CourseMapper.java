package pers.joy.mapper;

import org.apache.ibatis.annotations.Param;
import pers.joy.entity.Course;

import java.util.List;

public interface CourseMapper {

    /**
     * query all the course names
     */
    List<String> queryCourseName();

    /**
     * query course code by course name
     */
    String queryCodeByName(String name);

    /**
     * query whether the course code exists
     */
    List<Course> existCourseCode(String code);

    /**
     * query all courses
     */
    String queryCourseSum();

    List<Course> queryAllCourses(@Param("begin") int begin, @Param("pageSize") int pageSize);

    /**
     * query course by cCode
     */
    String queryCourseSumByCode(String code);

    List<Course> queryCourseByCode(@Param("code") String code,
                                         @Param("begin") int begin, @Param("pageSize") int pageSize);

    Course queryCourseForGrade(String code);

    /**
     * query whether there is a course item without a teacher
     */
    List<Course> existNoTeacherCourse(String code);

    /**
     * query course information by course code
     */
    Course queryCourseInfoByCode(String code);

    /**
     * query course by course code and teacher No
     */
    List<Course> queryCourseByCodeAndTeacherNo(@Param("code") String code, @Param("teacherNo") String teacherNo);

    /**
     * query selected courses for one student
     */
    List<Course> querySelectedCoursesByStudentNo(@Param("studentNo") String studentNo,
                                                 @Param("begin") int begin, @Param("pageSize") int pageSize);

    /**
     * query the course name that a teacher teaches
     */
    List<String> queryCourseNameListForTeacher(String tNo);

    /**
     * add new course
     */
    String queryMaxCode(String departmentNo);

    int insertCourse(Course course);

    /**
     * update teacher No for existed course item
     */
    int updateTeacherNoForExistItem(@Param("code")String code, @Param("teacherNo") String teacherNo);

    /**
     * update course
     */
    int updateCourse(Course course);

    int updateCourseTeacher(String code);

    /**
     * delete course item
     */
    int deleteCourseByCodeAndTeacherNo(@Param("code") String code, @Param("teacherNo") String teacherNo);
}
