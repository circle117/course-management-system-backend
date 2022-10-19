package pers.joy.dao;

import pers.joy.entity.User;

import java.util.List;
import java.util.Map;

public interface StudentDao{

    User queryUserByUsernameAndPassword(String username, String password);

    List<User> queryAllStudent();

    int insertStudent(Map<String, String> user);

    int editStudent(String sNo, Map<String, String> updateStudent);

    int deleteStudent(String sNo);

}
