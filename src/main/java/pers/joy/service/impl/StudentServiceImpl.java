package pers.joy.service.impl;

import pers.joy.dao.StudentDao;
import pers.joy.dao.impl.StudentDaoImpl;
import pers.joy.entity.User;
import pers.joy.service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    private final StudentDao studentDao = new StudentDaoImpl();

    @Override
    public User signIn(User user) {
        return studentDao.queryStudentByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public List<User> getStudentList(int pageNum, int pageSize) {
        int begin = (pageNum-1)*pageSize;
        return studentDao.queryAllStudent(begin, pageSize);
    }

    @Override
    public String getStudentSum() {
        return studentDao.queryStudentSum();
    }

    @Override
    public List<User> getStudentListByName(String name, int pageNum, int pageSize) {
        int begin = (pageNum-1)*pageSize;
        return studentDao.queryStudentByName(name, begin, pageSize);
    }

    @Override
    public String getStudentSumByName(String name) {
        return studentDao.queryStudentSumByName(name);
    }

    @Override
    public int createStudent(User student) {
        if (studentDao.queryStudentBySNo(student.getNo())!=null) {
            return -1;
        }
        return studentDao.insertStudent(student);
    }

    @Override
    public int editStudent(User student) {
        return studentDao.updateStudent(student);
    }

    @Override
    public int deleteStudent(String sNo) {
        return studentDao.deleteStudent(sNo);
    }
}
