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
    public List<User> getStudentList() {
        return studentDao.queryAllStudent();
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
