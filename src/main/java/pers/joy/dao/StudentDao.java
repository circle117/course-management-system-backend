package pers.joy.dao;

import pers.joy.entity.User;

import java.util.List;
import java.util.Map;

public interface StudentDao{

    /**
     * query Student by username and password
     * @param username username
     * @param password password
     * @return user or null
     */
    User queryStudentByUsernameAndPassword(String username, String password);

    /**
     * query all students
     * @return list of students
     */
    List<User> queryAllStudent(int begin, int pageSize);

    /**
     * get the sum of students
     * @return a string
     */
    String queryStudentSum();

    /**
     * query student by student no
     * @param sNo student no
     * @return User class
     */
    User queryStudentBySNo(String sNo);

    /**
     * create new student
     * @param student User object
     * @return 1 or -1
     */
    int insertStudent(User student);

    /**
     * update student info
     * @param student User object
     * @return 1 or -1
     */
    int updateStudent(User student);

    /**
     * delete student
     * @param sNo student number
     * @return 1 or -1
     */
    int deleteStudent(String sNo);

}
