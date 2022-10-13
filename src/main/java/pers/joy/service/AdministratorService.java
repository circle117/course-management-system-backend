package pers.joy.service;

import pers.joy.entity.Course;
import pers.joy.entity.User;

import java.util.List;
import java.util.Map;

public interface AdministratorService extends UserService {
    List<User> getTeacherList();

    List<Course> getCourseList();

    List<User> getStudentList();

    /**
     * create new course
     * @param course
     * @return failed course code
     */
    int createCourse(Map<String, String> course);

    /**
     * delete course
     * @param course
     * @return
     */
    int deleteCourse(Course course);

    int editCourse(Course oldCourse, Course newCourse);

    int addTeacher(String courseCode, List<String> teacherList);

    int createStudent(Map<String, String> student);

    int editStudent(String sNo, Map<String, String> updateStudent);

    int deleteStudent(String sNo);

    int createTeacher(Map<String, String> teacher);

    int deleteTeacher(String tNo);
}
