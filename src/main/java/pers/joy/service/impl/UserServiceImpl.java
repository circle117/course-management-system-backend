package pers.joy.service.impl;

import pers.joy.dao.UserDao;
import pers.joy.dao.impl.UserDaoImpl;
import pers.joy.entity.User;
import pers.joy.service.UserService;

public class UserServiceImpl implements UserService {

    private final UserDao userDao = new UserDaoImpl();

    @Override
    public User signIn(String type, User user) {
        return userDao.queryUserByUsernameAndPassword(type, user.getUsername(), user.getPassword());
    }

}
