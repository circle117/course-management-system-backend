package pers.joy.service.impl;

import org.springframework.stereotype.Service;
import pers.joy.entity.Grade;
import pers.joy.entity.User;
import pers.joy.mapper.GradeMapper;
import pers.joy.mapper.UserMapper;
import pers.joy.service.TeacherService;
import pers.joy.utils.DatabaseUtils;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final UserMapper userMapper;
    private final GradeMapper gradeMapper;
    private final String type = "teacher";

    public TeacherServiceImpl(UserMapper userMapper, GradeMapper gradeMapper) {
        this.userMapper = userMapper;
        this.gradeMapper = gradeMapper;
    }

    @Override
    public User signIn(User user) {
        return userMapper.queryUserByUsernameAndPassword(type, user);
    }

    @Override
    public List<User> getTeacherList(int pageNum, int pageSize) {
        int begin = (pageNum-1)*pageSize;
        return userMapper.queryAllUser(type, begin, pageSize);
    }

    @Override
    public String getTeacherSum() {
        return userMapper.queryUserSum(type);
    }

    @Override
    public List<User> getTeacherListByName(String name, int pageNum, int pageSize) {
        int begin = (pageNum-1)*pageSize;
        return userMapper.queryUserByName(type, "%"+name+"%", begin, pageSize);
    }

    @Override
    public String getTeacherSumByName(String name) {
        return userMapper.queryUserSumByName(type, "%"+name+"%");
    }

    @Override
    public int createTeacher(User teacher) {
        // generate teacher no
        String currentMaxNo = userMapper.queryMaxNo("teacher", teacher.getDepartment().getNo()+"%");
        if (currentMaxNo==null) {
            currentMaxNo = teacher.getDepartment().getNo()+"0000";
        }
        String nextNo = DatabaseUtils.generateNo(currentMaxNo);
        teacher.setNo(nextNo);

        // generate username
        String username = DatabaseUtils.generateUsername(teacher.getName());
        String no = userMapper.queryMaxUsername(username+"%");
        if (no==null) {
            username += "1";
        } else {
            no = no.substring(username.length());
            username += String.valueOf(Integer.parseInt(no)+1);
        }
        teacher.setUsername(username);
        teacher.setPassword("123456");
        return userMapper.insertUser(type, teacher);
    }

    @Override
    public int deleteTeacher(String teacherNo) {
        if (gradeMapper.queryGradeByTNo(teacherNo)!=null) {
            return -1;
        }
        return userMapper.deleteUser(type, teacherNo);
    }

    @Override
    public int editTeacher(User teacher) {
        return userMapper.updateUser(type, teacher);
    }
}
