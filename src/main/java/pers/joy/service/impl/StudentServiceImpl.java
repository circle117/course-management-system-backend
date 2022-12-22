package pers.joy.service.impl;

import org.springframework.stereotype.Service;
import pers.joy.entity.User;
import pers.joy.mapper.UserMapper;
import pers.joy.service.StudentService;
import pers.joy.utils.DatabaseUtils;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final UserMapper userMapper;
    private final String type = "student";

    public StudentServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User signIn(User user) {
        return userMapper.queryUserByUsernameAndPassword(type, user);
    }

    @Override
    public List<User> getStudentList(int pageNum, int pageSize) {
        int begin = (pageNum-1)*pageSize;
        return userMapper.queryAllUser(type, begin, pageSize);
    }

    @Override
    public String getStudentSum() {
        return userMapper.queryUserSum(type);
    }

    @Override
    public List<User> getStudentListByName(String name, int pageNum, int pageSize) {
        int begin = (pageNum-1)*pageSize;
        return userMapper.queryUserByName(type, "%"+name+"%", begin, pageSize);
    }

    @Override
    public String getStudentSumByName(String name) {
        return userMapper.queryUserSumByName(type, "%"+name+"%");
    }

    @Override
    public int createStudent(User student) {
        // generate student no
        String currentMaxNo = userMapper.queryMaxNo(type, student.getNo().substring(2, 4)+"%");
        if (currentMaxNo==null) {
            currentMaxNo = student.getNo().substring(2, 4)+"000000";
        }
        String nextNo = DatabaseUtils.generateNo(currentMaxNo);
        student.setNo(nextNo);

        // generate username
        String username = DatabaseUtils.generateUsername(student.getName());
        String no = userMapper.queryMaxUsername(username+"%");
        if (no==null) {
            username += "1";
        } else {
            no = no.substring(username.length());
            username += String.valueOf(Integer.parseInt(no)+1);
        }
        student.setUsername(username);
        student.setPassword("123456");
        return userMapper.insertUser(type, student);
    }

    @Override
    public int editStudent(User student) {
        return userMapper.updateUser(type, student);
    }

    @Override
    public int deleteStudent(String studentNo) {
        return userMapper.deleteUser(type, studentNo);
    }
}
