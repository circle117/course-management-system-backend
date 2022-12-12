package pers.joy.mapper;

import pers.joy.entity.User;

public interface AdministratorMapper{
    /**
     * query Administrator by username and password
     * @param user User object
     * @return user or null
     */
    User queryUserByUsernameAndPassword(User user);

}
