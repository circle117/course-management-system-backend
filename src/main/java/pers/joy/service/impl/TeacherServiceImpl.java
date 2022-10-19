package pers.joy.service.impl;

import pers.joy.dao.CourseDao;
import pers.joy.dao.GradeDao;
import pers.joy.dao.impl.CourseDaoImpl;
import pers.joy.dao.impl.GradeDaoImpl;
import pers.joy.dao.impl.TeacherDaoImpl;
import pers.joy.entity.Grade;
import pers.joy.entity.User;
import pers.joy.service.TeacherService;

import java.util.List;

public class TeacherServiceImpl implements TeacherService {

    private final CourseDao courseDao = new CourseDaoImpl();
    private final GradeDao gradeDao = new GradeDaoImpl();
    private final pers.joy.dao.TeacherDao teacherDao = new TeacherDaoImpl();

    @Override
    public User signIn(User user) {
        return teacherDao.queryTeacherByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public List<Object> getCourseNameListForTeacher(String tNo) {
        return courseDao.queryCourseNameListForTeacher(tNo);
    }

    @Override
    public List<Grade> getCompletedCourseStudentForTeacher(String cName, String tNo) {
        return gradeDao.queryForCompletedCourseStudentForTeacher(cName, tNo);
    }

    @Override
    public List<User> getSelectedCourseStudentForTeacher(String cName, String tNo) {
        return gradeDao.queryForSelectedCourseStudentForTeacher(cName, tNo);
    }
}
