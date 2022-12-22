package pers.joy.service.impl;

import org.springframework.stereotype.Service;
import pers.joy.entity.User;
import pers.joy.mapper.UserMapper;
import pers.joy.service.AdministratorService;

@Service
public class AdministratorServiceImpl implements AdministratorService {

    private final UserMapper userMapper;

    public AdministratorServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User signIn(User user) {
        return userMapper.queryUserByUsernameAndPassword("administrator", user);
    }
}
