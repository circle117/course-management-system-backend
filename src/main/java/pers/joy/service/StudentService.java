package pers.joy.service;

import pers.joy.entity.User;

import java.util.List;

public interface StudentService{

    User signIn(User user);

    List<User> getStudentList(int pageNum, int pageSize);

    String getStudentSum();

    int createStudent(User student);

    int editStudent(User student);

    int deleteStudent(String sNo);
}
