package pers.joy.dao;

import pers.joy.entity.Course;

import java.util.List;

public interface CourseDao {
    /**
     * query course by cCode
     * @param courseCode cCode
     * @param begin page number
     * @param pageSize page size
     * @return list of the course with different teacher
     */
    List<Course> queryCourseByCourseCode(String courseCode, int begin, int pageSize);

    /**
     * get sum of courses
     * @param courseCode course code
     * @return a String object
     */
    String queryCourseSumByCCode(String courseCode);

    /**
     * query selected courses for one student
     * @param sNo student number( no in table student)
     * @return list of courses
     */
    List<Course> querySelectedCoursesBySNo(String sNo, int begin, int pageSize);

    /**
     * query all exist courses
     * @return list of all the courses
     */
    List<Course> queryAllCourse(int begin, int pageSize);

    /**
     * query the sum of courses
     * @return a String
     */
    String queryCourseSum();

    /**
     * query all the course names
     * @return list of course names (String)
     */
    List<String> queryCourseName();

    /**
     * add new course
     * @param course Course Object
     * @return if success return 1; else return -1
     */
    int insertCourse(Course course);

    /**
     * delete course item
     * @param courseCode course code
     * @param teacherNo teacher number
     * @return if success return 1; else return -1
     */
    int deleteCourseByCCodeAndTNo(String courseCode, String teacherNo);

    /**
     * update course
     * @param course Course object
     * @return  if success return >0; else return -1
     */
    int updateCourse(Course course);

    /**
     * query whether the course code exists
     * @param courseCode course code (String)
     * @return list of courses
     */
    List<Course> existCourseCode(String courseCode);

    /**
     * query whether there is a course item withour tNo
     * @param courseCode course code (String)
     * @return course or null
     */
    List<Course> existNoTeacherCourse(String courseCode);

    /**
     * update TNo for existed course item
     * @param courseCode course code
     * @param tNo teacher No
     * @return if success return >0; else return -1
     */
    int updateTNoForExistItem(String courseCode, String tNo);

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
    List<Course> queryCourseByCCodeAndTNo(String courseCode, String tNo);

    /**
     * query course code by course name
     * @param cName course name
     * @return course code or null
     */
    String queryCCodeByName(String cName);

    /**
     * query the course name that a teacher teaches
     * @param tNo teacher number
     * @return list of course names
     */
    List<String> queryCourseNameListForTeacher(String tNo);



}
