package pers.joy.dao;

import pers.joy.entity.User;

public interface AdministratorDao{

    User queryUserByUsernameAndPassword(String username, String password);

}
