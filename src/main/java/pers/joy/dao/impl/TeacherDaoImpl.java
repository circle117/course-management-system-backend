package pers.joy.dao.impl;

import org.springframework.stereotype.Repository;
import pers.joy.entity.User;

import java.util.List;

@Repository
public class TeacherDaoImpl extends UserDao implements pers.joy.dao.TeacherDao {

    private final String tableName = "teacher";

    @Override
    public User queryTeacherByUsernameAndPassword(String username, String password) {
        return queryUserByUsernameAndPassword(tableName, username, password);
    }

    @Override
    public User queryTeacherByTNo(String tNo) {
        return queryUserByNo(tableName, tNo);
    }

    @Override
    public String queryTNoByTNameAndCCode(String TName, String CCode) {
        String sql = "select teacher.`no` from course inner join teacher on course.tNo = teacher.no " +
                "where tName = ? and cCode = ?";
        return (String)queryForSingleValue(sql, TName, CCode);
    }

    @Override
    public List<User> queryAllTeacher(int begin, int pageSize) {
        return queryAllUser(tableName, begin, pageSize);
    }

    @Override
    public String queryTeacherSum() {
        return queryUserSum(tableName);
    }

    @Override
    public List<User> queryTeacherByName(String name, int begin, int pageSize) {
        return queryUserByName(tableName, name, begin, pageSize);
    }

    @Override
    public String queryTeacherSumByName(String name) {
        return queryUserSumByName(tableName, name);
    }

    @Override
    public int insertTeacher(User teacher) {
        return insertUser(tableName, teacher);
    }

    @Override
    public int deleteTeacher(String tNo) {
        return deleteUser(tableName, tNo);
    }

    @Override
    public int updateTeacher(User teacher) {
        return updateUser(tableName, teacher);
    }
}
