package pers.joy.dao.impl;

import pers.joy.entity.User;

public class AdministratorDao extends UserDao implements pers.joy.dao.AdministratorDao {

    private final String tableName = "administrator";

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        return queryUserByUsernameAndPassword(tableName, username, password);
    }
}
