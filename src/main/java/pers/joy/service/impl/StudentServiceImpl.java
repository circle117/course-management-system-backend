package pers.joy.service.impl;

import pers.joy.dao.CourseDao;
import pers.joy.dao.GradeDao;
import pers.joy.dao.impl.CourseDaoImpl;
import pers.joy.dao.impl.GradeDaoImpl;
import pers.joy.entity.Course;
import pers.joy.entity.SelectCourse;
import pers.joy.entity.User;
import pers.joy.service.StudentService;

import java.util.ArrayList;
import java.util.List;

public class StudentServiceImpl extends UserServiceImpl implements StudentService {

    private final CourseDao courseDao = new CourseDaoImpl();
    private final GradeDao gradeDao = new GradeDaoImpl();

    @Override
    public List<Course> searchByCode(String courseCode) {
        return courseDao.queryCourseByCourseCode(courseCode);
    }

    @Override
    public List<Course> getSelectedCourses(String sNo) {
        return courseDao.querySelectedCourses(sNo);
    }

    @Override
    public List<String> selectCourse(String sNo, List<Course> courseList) {
        List<SelectCourse> selectCourseList = new ArrayList<>();
        for (Course c:courseList) {
            selectCourseList.add(new SelectCourse(sNo, c.getCCode(), c.getTNo()));
        }
        return gradeDao.insertSelectCourse(selectCourseList);
    }

    @Override
    public void dropCourse(String sNo, List<Course> courseList) {
        List<SelectCourse> selectCourseList = new ArrayList<>();
        for (Course c:courseList) {
            selectCourseList.add(new SelectCourse(sNo, c.getCCode(), c.getTNo()));
        }
        gradeDao.deleteCourse(selectCourseList);
    }

    @Override
    public List<SelectCourse> getCompletedCourses(String sNo) {
        return gradeDao.queryForCompletedCourses(sNo);
    }

    @Override
    public float getGPA(List<SelectCourse> selectCourseList) {
        int gradeSum = 0;
        int creditSum = 0;
        for(SelectCourse c: selectCourseList) {
            gradeSum += c.getGrade()*c.getCredit();
            creditSum += c.getCredit();
        }
        return (float) gradeSum/creditSum;
    }
}
