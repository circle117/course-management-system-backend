package pers.joy.service.impl;

import pers.joy.dao.CourseDao;
import pers.joy.dao.GradeDao;
import pers.joy.dao.impl.CourseDaoImpl;
import pers.joy.dao.impl.GradeDaoImpl;
import pers.joy.dao.impl.StudentDaoImpl;
import pers.joy.entity.Course;
import pers.joy.entity.Grade;
import pers.joy.entity.User;
import pers.joy.service.StudentService;

import java.util.ArrayList;
import java.util.List;

public class StudentServiceImpl implements StudentService {

    private final CourseDao courseDao = new CourseDaoImpl();
    private final GradeDao gradeDao = new GradeDaoImpl();
    private final pers.joy.dao.StudentDao studentDao = new StudentDaoImpl();

    @Override
    public User signIn(User user) {
        return studentDao.queryStudentByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public List<Course> searchByCode(String courseCode) {
        return courseDao.queryCourseByCourseCode(courseCode);
    }

    @Override
    public List<Course> getSelectedCourses(String sNo) {
        return courseDao.querySelectedCoursesBySNo(sNo);
    }

    @Override
    public List<String> selectCourse(List<Grade> gradeList) {
        List<String> failSelectedCourse = new ArrayList<>();
        for (Grade grade:gradeList) {
            if (gradeDao.insertGrade(grade)<0) {
                failSelectedCourse.add(grade.getCCode());
            }
        }
        return failSelectedCourse;
    }

    @Override
    public void dropCourse(List<Grade> gradeList) {
        for (Grade grade:gradeList) {
            gradeDao.deleteGrade(grade);
        }
    }

    @Override
    public List<Grade> getCompletedCourses(String sNo) {
        return gradeDao.queryCompletedCoursesBySNo(sNo);
    }

    @Override
    public float getGPA(List<Grade> gradeList) {
        int gradeSum = 0;
        int creditSum = 0;
        for(Grade c: gradeList) {
            gradeSum += c.getGrade()*c.getCredit();
            creditSum += c.getCredit();
        }
        return (float) gradeSum/creditSum;
    }
}
