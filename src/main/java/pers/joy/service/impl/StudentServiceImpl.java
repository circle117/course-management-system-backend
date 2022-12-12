package pers.joy.service.impl;

import org.springframework.stereotype.Service;
import pers.joy.entity.User;
import pers.joy.mapper.StudentMapper;
import pers.joy.service.StudentService;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentMapper studentMapper;

    public StudentServiceImpl(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    @Override
    public User signIn(User user) {
        return studentMapper.queryStudentByUsernameAndPassword(user);
    }

    @Override
    public List<User> getStudentList(int pageNum, int pageSize) {
        int begin = (pageNum-1)*pageSize;
        return studentMapper.queryAllStudent(begin, pageSize);
    }

    @Override
    public String getStudentSum() {
        return studentMapper.queryStudentSum();
    }

    @Override
    public List<User> getStudentListByName(String name, int pageNum, int pageSize) {
        int begin = (pageNum-1)*pageSize;
        return studentMapper.queryStudentByName("%"+name+"%", begin, pageSize);
    }

    @Override
    public String getStudentSumByName(String name) {
        return studentMapper.queryStudentSumByName("%"+name+"%");
    }

    @Override
    public int createStudent(User student) {
        if (studentMapper.queryStudentBySNo(student.getNo()).size()>0) {
            return -1;
        }
        return studentMapper.insertStudent(student);
    }

    @Override
    public int editStudent(User student) {
        return studentMapper.updateStudent(student);
    }

    @Override
    public int deleteStudent(String sNo) {
        return studentMapper.deleteStudent(sNo);
    }
}
