package pers.joy.service;

import pers.joy.entity.User;

public interface AdministratorService{

    /**
     * administrator sign in
     * @param user User object
     * @return User object
     */
    User signIn(User user);
}
