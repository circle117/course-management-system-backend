package pers.joy.dao.impl;

import pers.joy.dao.UserDao;
import pers.joy.entity.User;

public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public User queryUserByUsername(String type, String username) {
        String sql = String.format("select `no`, `name`, 'password' from %s where username = ?", type);
        return queryForOne(User.class, sql, username);
    }

    @Override
    public User queryUserByUsernameAndPassword(String type, String username, String password) {
        String sql;
        if (type.equals("student")) {
            sql = "select `no`, `name` from student where username = ? and password = ?";
        }
        else if (type.equals("teacher")) {
            sql = "";
        }
        else if (type.equals("administrator")) {
            sql = "select `aNo` as `no`, `aName` as `name` from administrator where username = ? and password = ?";
        }
        else {
            sql = "";
        }
        return queryForOne(User.class, sql, username, password);
    }
}
