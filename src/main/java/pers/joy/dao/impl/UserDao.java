package pers.joy.dao.impl;

import pers.joy.entity.User;

public abstract class UserDao extends BaseDao{

    protected User queryUserByUsernameAndPassword(String tableName, String username, String password) {
        String sql = String.format("select `no`, `name` from %s where username = ? and password = ?", tableName);
        return queryForOne(User.class, sql, username, password);
    }

    protected User queryUserByNo(String tableName, String no) {
        String sql = String.format("select * from %s where no = ?", tableName);
        return queryForOne(User.class, sql, no);
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
