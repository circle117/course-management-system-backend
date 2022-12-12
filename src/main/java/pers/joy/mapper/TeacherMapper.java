package pers.joy.mapper;

import org.apache.ibatis.annotations.Param;
import pers.joy.entity.User;

import java.util.List;

public interface TeacherMapper {
    /**
     * query Teacher by username and password
     * @param user User object
     * @return user or null
     */
    User queryTeacherByUsernameAndPassword(User user);

    /**
     * get the sum of teachers
     * @return a string
     */
    String queryTeacherSum();

    /**
     * query all teacher
     * @return list of teachers
     */
    List<User> queryAllTeacher(@Param("begin") int begin, @Param("pageSize") int pageSize);

    /**
     * get the sum of teachers by name
     * @return a string
     */
    String queryTeacherSumByName(String name);

    /**
     * query teachers by teacher name
     * @param name teacher name
     * @return list of User object
     */
    List<User> queryTeacherByName(@Param("name") String name,
                                  @Param("begin") int begin, @Param("pageSize") int pageSize);

    /**
     * query teacher by teacher No
     * @param tNo teacher No
     * @return User object or null
     */
    List<User> queryTeacherByTNo(String tNo);

    /**
     * create new teacher
     * @param teacher User object
     * @return 1 or -1
     */
    int insertTeacher(User teacher);

    /**
     * update teacher info
     * @param teacher User object
     * @return 1 or -1
     */
    int updateTeacher(User teacher);

    /**
     * delete teacher
     * @param tNo teacher No
     * @return 1 or -1
     */
    int deleteTeacher(String tNo);
}
