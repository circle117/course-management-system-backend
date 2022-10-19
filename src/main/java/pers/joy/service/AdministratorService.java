package pers.joy.service;

import pers.joy.entity.Course;
import pers.joy.entity.Grade;
import pers.joy.entity.User;

import java.util.List;
import java.util.Map;

public interface AdministratorService{

    User signIn(User user);

    List<User> getTeacherList();

    List<Course> getCourseList();

    List<User> getStudentList();

    int createCourse(Course course);

    int deleteCourse(Course course);

    int editCourse(Course course);

    List<String> addTeacher(String courseCode, List<String> teacherList);

    int createStudent(Map<String, String> student);

    int editStudent(String sNo, Map<String, String> updateStudent);

    int deleteStudent(String sNo);

    int createTeacher(User teacher);

    int deleteTeacher(String tNo);

    int editTeacher(User teacher);

    // grade management
    List<Object> getCourseNameList();

    List<Grade> getCompletedCourseStudent(String cName);

    List<User> getSelectedCourseStudent(String cName);

    int inputGrade(String sNo, String cName, String grade);
}
