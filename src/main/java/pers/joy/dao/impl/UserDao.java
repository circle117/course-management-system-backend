package pers.joy.dao.impl;

import pers.joy.entity.User;

import java.util.List;

public abstract class UserDao extends BaseDao {

    protected User queryUserByUsernameAndPassword(String tableName, String username, String password) {
        String sql = String.format("select `no`, `name` from %s where username = ? and password = ?", tableName);
        return queryForObject(User.class, sql, username, password);
    }

    protected User queryUserByNo(String tableName, String no) {
        String sql = String.format("select * from %s where no = ?", tableName);
        return queryForObject(User.class, sql, no);
    }

    protected List<User> queryAllUser(String tableName, int begin, int pageSize) {
        String sql = String.format("select * from %s order by no limit ?, ?", tableName);
        return queryForList(User.class, sql, begin, pageSize);
    }

    protected String queryUserSum(String tableName) {
        String sql = String.format("select count(*) from %s", tableName);
        return queryForSingleValue(String.class, sql);
    }

    protected List<User> queryUserByName(String tableName, String name, int begin, int pageSize) {
        String sql = String.format("select * from %s where name like ? order by no limit ?, ?", tableName);
        name = "%"+name+"%";
        return queryForList(User.class, sql, name, begin, pageSize);
    }

    protected String queryUserSumByName(String tableName, String name) {
        String sql = String.format("select count(*) from %s where name like ?", tableName);
        return queryForSingleValue(String.class, sql, "%"+name+"%");

    }

    protected int insertUser(String tableName, User user) {
        String sql = String.format("insert into %s values(?,?,?,?,?,?,?)", tableName);
        return update(sql, user.getNo(), user.getName(), user.getDepartment(), user.getGender(),
                user.getBirthday(), user.getUsername(), user.getPassword());
    }

    protected int deleteUser(String tableName, String no) {
        String sql = String.format("delete from %s where no = ?", tableName);
        return update(sql, no);
    }

    protected int updateUser(String tableName, User user) {
        String sql = String.format("update %s set name=?, department=?, " +
                "gender=?, birthday=? where no = ?", tableName);
        return update(sql, user.getName(), user.getDepartment(), user.getGender(), user.getBirthday(), user.getNo());
    }
}
