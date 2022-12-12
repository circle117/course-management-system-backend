package pers.joy.mapper;

import org.apache.ibatis.annotations.Param;
import pers.joy.entity.Grade;
import pers.joy.entity.User;

import java.util.List;

public interface GradeMapper {

    /**
     * query student who completed certain course
     * @param cName course name
     * @return list of Grade object
     */
    List<Grade> queryCompletedCourseStudentByCName(String cName);

    /**
     * query students who completed the course for teacher
     * @param cName course name
     * @param tNo teacher No
     * @return list of Grade object
     */
    List<Grade> queryCompletedCourseStudentByCNameAndTeacher(@Param("cName") String cName, @Param("tNo") String tNo);

    /**
     * query students who are currently taking the certain course
     * @param cName course name
     * @return list of User object
     */
    List<User> querySelectedCourseStudentByCName(String cName);

    /**
     * query grade by teacher No
     * @param tNo teacher No
     * @return list of Grade object
     */
    List<Grade> queryGradeByTNo(String tNo);

    /**
     * check if a student has taken one course
     * @param grade Grade object
     * @return List of Grade Object
     */
    List<Grade> queryGradeByCCodeAndSNo(Grade grade);

    /**
     * query for completed courses (have grade)
     * @param sNo student number
     * @return list of courses with grade and point
     */
    List<Grade> queryCompletedCoursesBySNo(String sNo);

    /**
     * query number of courses a student is taking
     * @param sNo student No
     * @return int
     */
    String queryGradeSumBySNo(String sNo);

    /**
     * query students who are currently taking the course
     * @param cName course name
     * @param tNo teacher No
     * @return list of Grade object
     */
    List<User> querySelectedCourseStudentByCNameAndTeacher(@Param("cName") String cName, @Param("tNo") String tNo);

    /**
     * insert grade
     * @param grade Grade object
     * @return 1 or -1
     */
    int insertGrade(Grade grade);

    /**
     * input grade and point
     * @param sNo student No
     * @param cCode course code
     * @param grade grade [0, 100]
     * @param point point [0, 4.0]
     * @return 1 or -1
     */
    int updateGrade(@Param("sNo") String sNo, @Param("cCode") String cCode,
                    @Param("grade") String grade, @Param("point") String point);

    /**
     * delete courses in grade table
     * @param grade list of Grade object including sNo, cCode, tNo
     */
    void deleteGrade(Grade grade);
}
