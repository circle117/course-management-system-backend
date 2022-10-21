package pers.joy.dao;

import pers.joy.entity.User;

public interface AdministratorDao{

    /**
     * query Administrator by username and password
     * @param username username
     * @param password password
     * @return user or null
     */
    User queryUserByUsernameAndPassword(String username, String password);

}
