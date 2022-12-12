package pers.joy.service.impl;

import org.springframework.stereotype.Service;
import pers.joy.entity.Grade;
import pers.joy.entity.User;
import pers.joy.mapper.GradeMapper;
import pers.joy.mapper.TeacherMapper;
import pers.joy.service.TeacherService;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherMapper teacherMapper;
    private final GradeMapper gradeMapper;

    public TeacherServiceImpl(TeacherMapper teacherMapper, GradeMapper gradeMapper) {
        this.teacherMapper = teacherMapper;
        this.gradeMapper = gradeMapper;
    }

    @Override
    public User signIn(User user) {
        return teacherMapper.queryTeacherByUsernameAndPassword(user);
    }

    @Override
    public List<User> getTeacherList(int pageNum, int pageSize) {
        int begin = (pageNum-1)*pageSize;
        return teacherMapper.queryAllTeacher(begin, pageSize);
    }

    @Override
    public String getTeacherSum() {
        return teacherMapper.queryTeacherSum();
    }

    @Override
    public List<User> getTeacherListByName(String name, int pageNum, int pageSize) {
        int begin = (pageNum-1)*pageSize;
        return teacherMapper.queryTeacherByName("%"+name+"%", begin, pageSize);
    }

    @Override
    public String getTeacherSumByName(String name) {
        return teacherMapper.queryTeacherSumByName("%"+name+"%");
    }

    @Override
    public int createTeacher(User teacher) {
        if (teacherMapper.queryTeacherByTNo(teacher.getNo()).size()>0) {
            return -1;
        }
        return teacherMapper.insertTeacher(teacher);
    }

    @Override
    public int deleteTeacher(String tNo) {
        List<Grade> res = gradeMapper.queryGradeByTNo(tNo);
        if (gradeMapper.queryGradeByTNo(tNo).size()>0) {
            return -1;
        }
        return teacherMapper.deleteTeacher(tNo);
    }

    @Override
    public int editTeacher(User teacher) {
        return teacherMapper.updateTeacher(teacher);
    }
}
