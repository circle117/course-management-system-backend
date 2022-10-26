package pers.joy.service;

import pers.joy.entity.User;

import java.util.List;

public interface TeacherService{
    User signIn(User user);

    List<User> getTeacherList(int pageNum, int pageSize);

    String getTeacherSum();

    List<User> getTeacherListByName(String name, int pageNum, int pageSize);

    String getTeacherSumByName(String name);

    int createTeacher(User teacher);

    int deleteTeacher(String tNo);

    int editTeacher(User teacher);
}
