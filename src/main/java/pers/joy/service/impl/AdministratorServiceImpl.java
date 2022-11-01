package pers.joy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.joy.dao.AdministratorDao;
import pers.joy.entity.User;
import pers.joy.service.AdministratorService;

@Service
public class AdministratorServiceImpl implements AdministratorService {

    private final AdministratorDao administratorDao;

    @Autowired
    public AdministratorServiceImpl(AdministratorDao administratorDao) {
        this.administratorDao = administratorDao;
    }

    @Override
    public User signIn(User user) {
        return administratorDao.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }
}
