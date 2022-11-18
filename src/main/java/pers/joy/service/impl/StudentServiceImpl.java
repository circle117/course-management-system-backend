package pers.joy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.joy.dao.StudentDao;
import pers.joy.entity.User;
import pers.joy.service.StudentService;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentDao studentDao;

    @Autowired
    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

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
        if (studentDao.queryStudentBySNo(student.getNo()).size()>0) {
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
