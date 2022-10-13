package pers.joy.dao;

import pers.joy.entity.User;

import java.util.List;
import java.util.Map;

public interface UserDao {

    /**
     * query user by username
     * @param type
     * @param username
     * @return null or info
     */
    User queryUserByUsername(String type, String username);

    /**
     * query user by username and password
     * @param type
     * @param username
     * @return null or info
     */
    User queryUserByUsernameAndPassword(String type, String username, String password);
}
