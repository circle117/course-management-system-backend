package pers.joy.dao;

import pers.joy.entity.Course;
import pers.joy.entity.SelectCourse;

import java.util.List;
import java.util.Map;

public interface CourseDao {
    List<Course> queryCourseByCourseCode(String courseCode);

    List<Course> querySelectedCourses(String sNo);

    List<Course> queryAllCourse();

    List<Object> queryCourseName();

    int insertCourse(Map<String, String> course);

    int deleteCourse(Course course);

    int updateCourse(String cCode, Map<String, String> editCourse);

    List<Course> existCourseCode(String courseCode);

    List<Course> existNoTeacherCourse(String courseCode);

    int updateTNoForExistItem(String courseCode, String tNo);

    Course queryCourseInfo(String courseCode);

    int insertTNo(Course courseInfo, List<String> teacherList);

    List<Course> existCourseAndTeacher(String courseCode, String tNo);

    String queryCCodeByName(String cName);

    List<Object> queryCourseNameListForTeacher(String tNo);

}
