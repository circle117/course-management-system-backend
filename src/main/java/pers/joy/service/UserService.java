package pers.joy.service;

import pers.joy.entity.User;


public interface UserService {
    // one service one method
    User signIn(String type, User user);
}
