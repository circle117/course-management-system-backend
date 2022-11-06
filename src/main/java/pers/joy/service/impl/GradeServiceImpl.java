package pers.joy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.joy.dao.CourseDao;
import pers.joy.dao.GradeDao;
import pers.joy.entity.Grade;
import pers.joy.entity.User;
import pers.joy.service.GradeService;

import java.util.ArrayList;
import java.util.List;

@Service
public class GradeServiceImpl implements GradeService {

    private final CourseDao courseDao;
    private final GradeDao gradeDao;

    @Autowired
    public GradeServiceImpl(CourseDao courseDao, GradeDao gradeDao) {
        this.courseDao = courseDao;
        this.gradeDao = gradeDao;
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
    public List<Grade> getCompletedCourseStudent(String cName, String tNo) {
        return gradeDao.queryCompletedCourseStudentByCNameAndTeacher(cName, tNo);
    }

    @Override
    public List<User> getSelectedCourseStudent(String cName, String tNo) {
        return gradeDao.querySelectedCourseStudentByCNameAndTeacher(cName, tNo);
    }

    @Override
    public int submitGrade(String sNo, String cName, String grade) {
        String cCode = courseDao.queryCCodeByName(cName);
        String point = String.valueOf(getPoint(Integer.parseInt(grade)));
        return gradeDao.updateGrade(sNo, cCode, grade, point);
    }

    @Override
    public List<Grade> getCompletedCourseStudent(String cName) {
        return gradeDao.queryCompletedCourseStudentByCName(cName);
    }

    @Override
    public List<User> getSelectedCourseStudent(String cName) {
        return gradeDao.querySelectedCourseStudentByCName(cName);
    }

    @Override
    public String getSelectedCourseSum(String sNo) {
        return gradeDao.queryGradeSumBySNo(sNo);
    }

    private float getPoint(int grade) {
        if (grade >= 90) {
            return 4.0f;
        } else if (grade>=85) {
            return 3.7f;
        } else if (grade>=82) {
            return 3.3f;
        } else if (grade>=78) {
            return 3.0f;
        } else if (grade>=75) {
            return 2.7f;
        } else if (grade>=72) {
            return 2.3f;
        } else if (grade>=68) {
            return 2.0f;
        } else if (grade>=66) {
            return 1.7f;
        } else if (grade>=64) {
            return 1.5f;
        } else if (grade>=60) {
            return 1.0f;
        } else {
            return 0.0f;
        }
    }
}
