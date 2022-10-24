package pers.joy.service.impl;

import pers.joy.dao.GradeDao;
import pers.joy.dao.TeacherDao;
import pers.joy.dao.impl.GradeDaoImpl;
import pers.joy.dao.impl.TeacherDaoImpl;
import pers.joy.entity.User;
import pers.joy.service.TeacherService;

import java.util.List;


public class TeacherServiceImpl implements TeacherService {

    private final TeacherDao teacherDao = new TeacherDaoImpl();
    private final GradeDao gradeDao = new GradeDaoImpl();

    @Override
    public User signIn(User user) {
        return teacherDao.queryTeacherByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public List<User> getTeacherList() {
        return teacherDao.queryAllTeacher();
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
