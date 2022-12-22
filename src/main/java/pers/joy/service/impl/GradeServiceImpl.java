package pers.joy.service.impl;

import org.springframework.stereotype.Service;
import pers.joy.entity.Grade;
import pers.joy.entity.User;
import pers.joy.mapper.CourseMapper;
import pers.joy.mapper.GradeMapper;
import pers.joy.service.GradeService;

import java.util.ArrayList;
import java.util.List;

@Service
public class GradeServiceImpl implements GradeService {

    private final CourseMapper courseMapper;
    private final GradeMapper gradeMapper;

    public GradeServiceImpl(CourseMapper courseMapper, GradeMapper gradeMapper) {
        this.courseMapper = courseMapper;
        this.gradeMapper = gradeMapper;
    }

    @Override
    public List<String> selectCourse(List<Grade> gradeList) {
        List<String> failSelectedCourse = new ArrayList<>();
        for (Grade grade:gradeList) {
            if (gradeMapper.queryGradeByCodeAndStudentNo(grade)!=null) {
                failSelectedCourse.add(grade.getCourse().getCode());
            } else {
                gradeMapper.insertGrade(grade);
            }
        }
        return failSelectedCourse;
    }

    @Override
    public int dropCourse(List<Grade> gradeList) {
        int cnt = 0;
        for (Grade grade:gradeList) {
            cnt += gradeMapper.deleteGrade(grade);
        }
        return cnt;
    }

    @Override
    public List<Grade> getCompletedCourses(String sNo) {
        return gradeMapper.queryCompletedCoursesByStudentNo(sNo);
    }

    @Override
    public List<Grade> getCompletedCourseStudent(String cName) {
        return gradeMapper.queryCompletedCourseStudentByCName(cName);
    }

    @Override
    public List<Grade> getCompletedCourseStudent(String cName, String tNo) {
        return gradeMapper.queryCompletedCourseStudentByCNameAndTeacher(cName, tNo);
    }

    @Override
    public List<User> getSelectedCourseStudent(String cName) {
        return gradeMapper.querySelectedCourseStudentByCName(cName);
    }

    @Override
    public List<User> getSelectedCourseStudent(String cName, String tNo) {
        return gradeMapper.querySelectedCourseStudentByCNameAndTeacher(cName, tNo);
    }

    @Override
    public int submitGrade(String sNo, String cName, String grade) {
        String cCode = courseMapper.queryCodeByName(cName);
        String point = String.valueOf(getPoint(Integer.parseInt(grade)));
        return gradeMapper.updateGrade(sNo, cCode, grade, point);
    }

    @Override
    public String getSelectedCourseSum(String sNo) {
        return gradeMapper.queryGradeSumByStudentNo(sNo);
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
