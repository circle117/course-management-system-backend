package pers.joy.mapper;

import org.apache.ibatis.annotations.Param;
import pers.joy.entity.Course;

import java.util.List;

public interface CourseMapper {

    /**
     * query all the course names
     * @return list of course names (String)
     */
    List<String> queryCourseName();

    /**
     * query course code by course name
     * @param cName course name
     * @return course code or null
     */
    String queryCCodeByName(String cName);

    /**
     * query whether the course code exists
     * @param courseCode course code (String)
     * @return list of courses
     */
    List<Course> existCourseCode(String courseCode);

    /**
     * query the sum of courses
     * @return a String
     */
    String queryCourseSum();

    /**
     * query all exist courses
     * @return list of all the courses
     */
    List<Course> queryAllCourses(@Param("begin") int begin, @Param("pageSize") int pageSize);

    /**
     * query whether there is a course item withour tNo
     * @param courseCode course code (String)
     * @return course or null
     */
    List<Course> existNoTeacherCourse(String courseCode);

    /**
     * query course information by course code
     * @param courseCode course code
     * @return course or null
     */
    Course queryCourseInfoByCCode(String courseCode);

    /**
     * query course by course code and teacher No
     * @param courseCode course code
     * @param tNo teacher No
     * @return course object or bull
     */
    List<Course> queryCourseByCCodeAndTNo(@Param("courseCode") String courseCode, @Param("tNo") String tNo);

    /**
     * query selected courses for one student
     * @param sNo student number( no in table student)
     * @return list of courses
     */
    List<Course> querySelectedCoursesBySNo(@Param("sNo") String sNo,
                                           @Param("begin") int begin, @Param("pageSize") int pageSize);

    /**
     * query course by cCode
     * @param courseCode cCode
     * @param begin page number
     * @param pageSize page size
     * @return list of the course with different teacher
     */
    List<Course> queryCourseByCourseCode(@Param("courseCode") String courseCode,
                                         @Param("begin") int begin, @Param("pageSize") int pageSize);

    /**
     * get sum of courses
     * @param courseCode course code
     * @return a String object
     */
    String queryCourseSumByCourseCode(String courseCode);

    /**
     * query the course name that a teacher teaches
     * @param tNo teacher number
     * @return list of course names
     */
    List<String> queryCourseNameListForTeacher(String tNo);

    /**
     * add new course
     * @param course Course Object
     * @return if success return 1; else return -1
     */
    int insertCourse(Course course);

    /**
     * update TNo for existed course item
     * @param courseCode course code
     * @param tNo teacher No
     * @return if success return >0; else return -1
     */
    int updateTNoForExistItem(@Param("courseCode")String courseCode, @Param("tNo") String tNo);

    /**
     * update course
     * @param course Course object
     * @return  if success return >0; else return -1
     */
    int updateCourse(Course course);

    /**
     * delete course item
     * @param courseCode course code
     * @param teacherNo teacher number
     * @return if success return 1; else return -1
     */
    int deleteCourseByCCodeAndTNo(@Param("courseCode") String courseCode, @Param("teacherNo") String teacherNo);
}
