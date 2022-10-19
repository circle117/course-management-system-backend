package pers.joy.dao;

import pers.joy.entity.Course;

import java.util.List;

public interface CourseDao {
    /**
     * query course by cCode
     * @param courseCode cCode
     * @return list of the course with different teacher
     */
    List<Course> queryCourseByCourseCode(String courseCode);

    /**
     * query selected courses for one student
     * @param sNo student number( no in table student)
     * @return list of courses
     */
    List<Course> querySelectedCoursesBySNo(String sNo);

    /**
     * query all exist courses
     * @return list of all the courses
     */
    List<Course> queryAllCourse();

    /**
     * query all the course names
     * @return list of course names (String)
     */
    List<Object> queryCourseName();

    /**
     * add new course
     * @param course Course Object
     * @return if success return 1; else return -1
     */
    int insertCourse(Course course);

    /**
     * delete course item
     * @param course Course Object
     * @return if success return 1; else return -1
     */
    int deleteCourseByCCode(Course course);

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
    Course existNoTeacherCourse(String courseCode);

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
    Course queryCourseByCCodeAndTNo(String courseCode, String tNo);

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
    List<Object> queryCourseNameListForTeacher(String tNo);

}
