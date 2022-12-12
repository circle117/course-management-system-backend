package pers.joy.mapper;

import org.apache.ibatis.annotations.Param;
import pers.joy.entity.User;

import java.util.List;

public interface StudentMapper {
    /**
     * query Student by username and password
     * @param user User object
     * @return user or null
     */
    User queryStudentByUsernameAndPassword(User user);

    /**
     * get the sum of students
     * @return a string
     */
    String queryStudentSum();

    /**
     * query all students
     * @return list of students
     */
    List<User> queryAllStudent(@Param("begin") int begin, @Param("pageSize") int pageSize);

    /**
     * get the sum of students
     * @return a string
     */
    String queryStudentSumByName(String name);


    /**
     * query all students
     * @return list of students
     */
    List<User> queryStudentByName(@Param("name") String name,
                                  @Param("begin") int begin, @Param("pageSize") int pageSize);

    /**
     * query student by student no
     * @param sNo student no
     * @return User class
     */
    List<User> queryStudentBySNo(String sNo);

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
