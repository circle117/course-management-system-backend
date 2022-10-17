package pers.joy.service;

import pers.joy.entity.Course;
import pers.joy.entity.SelectCourse;
import pers.joy.entity.User;

import java.util.List;
import java.util.Map;

public interface AdministratorService extends UserService {
    List<User> getTeacherList();

    List<Course> getCourseList();

    List<User> getStudentList();

    int createCourse(Map<String, String> course);

    int deleteCourse(Course course);

    int editCourse(String cCode, Map<String, String> editCourse);

    List<String> addTeacher(String courseCode, List<String> teacherList);

    int createStudent(Map<String, String> student);

    int editStudent(String sNo, Map<String, String> updateStudent);

    int deleteStudent(String sNo);

    int createTeacher(Map<String, String> teacher);

    int deleteTeacher(String tNo);

    int editTeacher(String tNo, Map<String, String> editTeacher);

    // grade management
    List<Object> getCourseNameList();

    List<SelectCourse> getCompletedCourseStudent(String cName);

    List<User> getSelectedCourseStudent(String cName);

    int inputGrade(String sNo, String cName, String grade);
}
