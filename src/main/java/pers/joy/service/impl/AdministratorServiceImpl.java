package pers.joy.service.impl;

import pers.joy.dao.CourseDao;
import pers.joy.dao.StudentDao;
import pers.joy.dao.TeacherDao;
import pers.joy.dao.impl.CourseDaoImpl;
import pers.joy.dao.impl.StudentDaoImpl;
import pers.joy.dao.impl.TeacherDaoImpl;
import pers.joy.entity.Course;
import pers.joy.entity.User;
import pers.joy.service.AdministratorService;

import java.util.List;
import java.util.Map;

public class AdministratorServiceImpl extends UserServiceImpl implements AdministratorService {

    private final TeacherDao teacherDao = new TeacherDaoImpl();
    private final CourseDao courseDao = new CourseDaoImpl();
    private final StudentDao studentDao = new StudentDaoImpl();

    @Override
    public List<User> getTeacherList() {
        return teacherDao.queryAllTeacher();
    }

    @Override
    public List<Course> getCourseList() {
        return courseDao.queryAllCourse();
    }

    @Override
    public List<User> getStudentList() {
        return studentDao.queryAllStudent();
    }

    @Override
    public int createCourse(Map<String, String> course) {
        boolean ifExist = courseDao.existCourseCode(course.get("cCode")).size()>0;
        if (ifExist) {
            return -1;
        }
        return courseDao.insertCourse(course);
    }

    @Override
    public int deleteCourse(Course course) {
        return courseDao.deleteCourse(course);
    }

    @Override
    public int editCourse(Course oldCourse, Course newCourse) {
        return courseDao.updateCourse(oldCourse, newCourse);
    }

    @Override
    public int addTeacher(String courseCode, List<String> teacherList) {
        if (teacherList.size()==0) {
            return -1;
        }
        int res = 0;
        // fill up the empty tNo
        if (courseDao.existNoTeacherCourse(courseCode).size()>0) {
            res += courseDao.updateTNoForExistItem(courseCode, teacherList.get(0));
            teacherList.remove(0);
            if (teacherList.size()==0) {
                return res;
            }
        }
        // add new items for other tNo
        Course courseInfo = courseDao.queryCourseInfo(courseCode);
        res += courseDao.insertTNo(courseInfo, teacherList);
        return res;
    }

    @Override
    public int createStudent(Map<String, String> student) {
        return studentDao.insertStudent(student);
    }

    @Override
    public int editStudent(String sNo, Map<String, String> updateStudent) {
        return studentDao.editStudent(sNo, updateStudent);
    }

    @Override
    public int deleteStudent(String sNo) {
        return studentDao.deleteStudent(sNo);
    }

    @Override
    public int createTeacher(Map<String, String> teacher) {
        return teacherDao.insertTeacher(teacher);
    }

    @Override
    public int deleteTeacher(String tNo) {
        return teacherDao.deleteTeacher(tNo);
    }
}
