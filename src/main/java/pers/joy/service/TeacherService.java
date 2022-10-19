package pers.joy.service;

import pers.joy.entity.Grade;
import pers.joy.entity.User;

import java.util.List;

public interface TeacherService{
    User signIn(User user);

    List<Object> getCourseNameListForTeacher(String tNo);

    List<Grade> getCompletedCourseStudentForTeacher(String cName, String tNo);

    List<User> getSelectedCourseStudentForTeacher(String cName, String tNo);
}
