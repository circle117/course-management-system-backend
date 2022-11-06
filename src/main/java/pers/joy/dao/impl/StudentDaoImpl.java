package pers.joy.dao.impl;

import org.springframework.stereotype.Repository;
import pers.joy.entity.User;

import java.util.List;

@Repository
public class StudentDaoImpl extends UserDao implements pers.joy.dao.StudentDao {

    private final String tableName = "student";

    @Override
    public User queryStudentByUsernameAndPassword(String username, String password) {
        return queryUserByUsernameAndPassword(tableName, username, password);
    }

    @Override
    public List<User> queryAllStudent(int begin, int pageSize) {
        return queryAllUser(tableName, begin, pageSize);
    }

    @Override
    public String queryStudentSum() {
        return queryUserSum(tableName);
    }

    @Override
    public List<User> queryStudentByName(String name, int begin, int pageSize) {
        return queryUserByName(tableName, name, begin, pageSize);
    }

    @Override
    public String queryStudentSumByName(String name) {
        return queryUserSumByName(tableName, name);
    }

    @Override
    public User queryStudentBySNo(String sNo) {
        return queryUserByNo(tableName, sNo);
    }

    @Override
    public int insertStudent(User student) {
        return insertUser(tableName, student);
    }

    @Override
    public int updateStudent(User student) {
        return updateUser(tableName, student);
    }

    @Override
    public int deleteStudent(String sNo) {
        return deleteUser(tableName, sNo);
    }
}
