package pers.joy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.joy.dao.GradeDao;
import pers.joy.dao.TeacherDao;
import pers.joy.entity.User;
import pers.joy.service.TeacherService;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherDao teacherDao;
    private final GradeDao gradeDao;

    @Autowired
    public TeacherServiceImpl(TeacherDao teacherDao, GradeDao gradeDao) {
        this.teacherDao = teacherDao;
        this.gradeDao = gradeDao;
    }

    @Override
    public User signIn(User user) {
        return teacherDao.queryTeacherByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public List<User> getTeacherList(int pageNum, int pageSize) {
        int begin = (pageNum-1)*pageSize;
        return teacherDao.queryAllTeacher(begin, pageSize);
    }

    @Override
    public String getTeacherSum() {
        return teacherDao.queryTeacherSum();
    }

    @Override
    public List<User> getTeacherListByName(String name, int pageNum, int pageSize) {
        int begin = (pageNum-1)*pageSize;
        return teacherDao.queryTeacherByName(name, begin, pageSize);
    }

    @Override
    public String getTeacherSumByName(String name) {
        return teacherDao.queryTeacherSumByName(name);
    }

    @Override
    public int createTeacher(User teacher) {
        if (teacherDao.queryTeacherByTNo(teacher.getNo())!=null) {
            return -1;
        }
        return teacherDao.insertTeacher(teacher);
    }

    @Override
    public int deleteTeacher(String tNo) {
        if (gradeDao.queryGradeByTNo(tNo).size()>0) {
            return -1;
        }
        return teacherDao.deleteTeacher(tNo);
    }

    @Override
    public int editTeacher(User teacher) {
        return teacherDao.updateTeacher(teacher);
    }
}
