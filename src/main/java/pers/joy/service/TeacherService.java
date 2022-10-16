package pers.joy.service;

import pers.joy.entity.SelectCourse;
import pers.joy.entity.User;

import java.util.List;

public interface TeacherService extends UserService{
    List<Object> getCourseNameListForTeacher(String tNo);

    List<SelectCourse> getCompletedCourseStudentForTeacher(String cName, String tNo);

    List<User> getSelectedCourseStudentForTeacher(String cName, String tNo);
}
