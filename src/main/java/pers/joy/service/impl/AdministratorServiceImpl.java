package pers.joy.service.impl;

import pers.joy.dao.impl.AdministratorDao;
import pers.joy.entity.User;
import pers.joy.service.AdministratorService;

public class AdministratorServiceImpl implements AdministratorService {

    private final pers.joy.dao.AdministratorDao administratorDao = new AdministratorDao();

    @Override
    public User signIn(User user) {
        return administratorDao.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }
}
