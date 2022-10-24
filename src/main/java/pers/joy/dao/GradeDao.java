package pers.joy.dao;

import pers.joy.entity.Grade;
import pers.joy.entity.User;

import java.util.List;

public interface GradeDao {

    /**
     * insert grade
     * @param grade Grade object
     * @return 1 or -1
     */
    int insertGrade(Grade grade);

    /**
     * delete courses in grade table
     * @param grade list of Grade object including sNo, cCode, tNo
     */
    void deleteGrade(Grade grade);

    /**
     * query for completed courses (have grade)
     * @param sNo student number
     * @return list of courses with grade and point
     */
    List<Grade> queryCompletedCoursesBySNo(String sNo);

    /**
     * query student who completed certain course
     * @param cName course name
     * @return list of Grade object
     */
    List<Grade> queryCompletedCourseStudentByCName(String cName);

    /**
     * query students who are currently taking the certain course
     * @param cName course name
     * @return list of User object
     */
    List<User> querySelectedCourseStudentByCName(String cName);

    /**
     * input grade and point
     * @param sNo student No
     * @param cCode course code
     * @param grade grade [0, 100]
     * @param point point [0, 4.0]
     * @return 1 or -1
     */
    int updateGrade(String sNo, String cCode, String grade, String point);

    /**
     * query students who completed the course for teacher
     * @param cName course name
     * @param tNo teacher No
     * @return list of Grade object
     */
    List<Grade> queryCompletedCourseStudentByCNameAndTeacher(String cName, String tNo);

    /**
     * query students who are currently taking the course
     * @param cName course name
     * @param tNo teacher No
     * @return list of Grade object
     */
    List<User> querySelectedCourseStudentByCNameAndTeacher(String cName, String tNo);

    /**
     * query grade by teacher No
     * @param tNo teacher No
     * @return list of Grade object
     */
    List<Grade> queryGradeByTNo(String tNo);
}
