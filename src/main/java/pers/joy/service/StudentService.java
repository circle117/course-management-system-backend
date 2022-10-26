package pers.joy.service;

import pers.joy.entity.User;

import java.util.List;

public interface StudentService{

    User signIn(User user);

    List<User> getStudentList(int pageNum, int pageSize);

    String getStudentSum();

    List<User> getStudentListByName(String name, int pageNum, int pageSize);

    String getStudentSumByName(String name);

    int createStudent(User student);

    int editStudent(User student);

    int deleteStudent(String sNo);
}
