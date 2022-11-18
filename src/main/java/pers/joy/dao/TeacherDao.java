package pers.joy.dao;

import pers.joy.entity.User;

import java.util.List;

public interface TeacherDao{

    /**
     * query Teacher by username and password
     * @param username username
     * @param password password
     * @return user or null
     */
    User queryTeacherByUsernameAndPassword(String username, String password);

    /**
     * query teacher by teacher No
     * @param tNo teacher No
     * @return User object or null
     */
    List<User> queryTeacherByTNo(String tNo);

    /**
     * query teacher by teacher name and course code
     * @param TName teacher name
     * @param CCode course code
     * @return teacher name or "null"
     */
    String queryTNoByTNameAndCCode(String TName, String CCode);

    /**
     * query all teacher
     * @return list of teachers
     */
    List<User> queryAllTeacher(int begin, int pageSize);

    /**
     * get the sum of teachers
     * @return a string
     */
    String queryTeacherSum();

    /**
     * query teachers by teacher name
     * @param name teacher name
     * @return list of User object
     */
    List<User> queryTeacherByName(String name, int begin, int pageSize);

    /**
     * get the sum of teachers by name
     * @return a string
     */
    String queryTeacherSumByName(String name);

    /**
     * create new teacher
     * @param teacher User object
     * @return 1 or -1
     */
    int insertTeacher(User teacher);

    /**
     * delete teacher
     * @param tNo teacher No
     * @return 1 or -1
     */
    int deleteTeacher(String tNo);

    /**
     * update teacher info
     * @param teacher User object
     * @return 1 or -1
     */
    int updateTeacher(User teacher);
}
