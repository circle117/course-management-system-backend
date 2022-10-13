package pers.joy.dao;

import pers.joy.entity.Course;
import pers.joy.entity.SelectCourse;

import java.util.List;
import java.util.Map;

public interface CourseDao {
    List<Course> queryCourseByCourseCode(String courseCode);

    List<Course> querySelectedCourses(String sNo);

    List<Course> queryAllCourse();

    int insertCourse(Map<String, String> course);

    int deleteCourse(Course course);

    int updateCourse(Course oldCourse, Course newCourse);

    List<Course> existCourseCode(String courseCode);

    List<Course> existNoTeacherCourse(String courseCode);

    int updateTNoForExistItem(String courseCode, String tNo);

    Course queryCourseInfo(String courseCode);

    int insertTNo(Course courseInfo, List<String> teacherList);

    List<Course> existCourseAndTeacher(String courseCode, String tNo);
}
