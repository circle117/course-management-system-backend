package pers.joy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.joy.entity.User;
import pers.joy.mapper.AdministratorMapper;
import pers.joy.service.AdministratorService;

@Service
public class AdministratorServiceImpl implements AdministratorService {

    private final AdministratorMapper administratorMapper;

    @Autowired
    public AdministratorServiceImpl(AdministratorMapper administratorMapper) {
        this.administratorMapper = administratorMapper;
    }

    @Override
    public User signIn(User user) {
        return administratorMapper.queryUserByUsernameAndPassword(user);
    }
}
