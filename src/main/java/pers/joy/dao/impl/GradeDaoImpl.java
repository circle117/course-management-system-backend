package pers.joy.dao.impl;

import pers.joy.dao.GradeDao;
import pers.joy.entity.Grade;
import pers.joy.entity.User;

import java.util.ArrayList;
import java.util.List;

public class GradeDaoImpl extends BaseDao implements GradeDao {
    @Override
    public List<String> insertSelectCourse(List<Grade> gradeList) {
        String sql = "insert into grade(sNo, cCode, tNo) values(?, ?, ?)";
        List<String> failedSelectCourseList = new ArrayList<>();
        for (Grade grade : gradeList) {
            int res = update(sql, grade.getSNo(), grade.getCCode(), grade.getTNo());
            if (res<0) {
                failedSelectCourseList.add(grade.getCCode());
            }
        }
        return failedSelectCourseList;
    }

    @Override
    public void deleteCourse(List<Grade> gradeList) {
        String sql = "delete from grade where sNo = ? and cCode = ? and tNo = ?";
        for (Grade grade : gradeList) {
            update(sql, grade.getSNo(), grade.getCCode(), grade.getTNo());
        }
    }

    @Override
    public List<Grade> queryForCompletedCourses(String sNo) {
        String sql = "select distinct `sNo`, grade.`cCode`, `grade`, `point`, course.`cName`, `credit` " +
                "from grade left join course on grade.cCode=course.cCode " +
                "where sNo = ? and grade is not null";
        return queryForList(Grade.class, sql, sNo);
    }

    @Override
    public List<Grade> queryForCompletedCourseStudent(String cName) {
        String sql = "select distinct `sNo`, `grade`, `point` " +
                "from grade inner join course on grade.cCode=course.cCode " +
                "where cName = ? and grade is not null";
        return queryForList(Grade.class, sql, cName);
    }

    @Override
    public List<User> queryForSelectedCourseStudent(String cName) {
        String sql = "select distinct student.`no`, student.`name` from " +
                "course inner join grade on course.cCode=grade.cCode " +
                "inner join student on student.no=grade.sNo where grade is null and " +
                "cName = ?";
        return queryForList(User.class, sql, cName);
    }

    @Override
    public int updateGrade(String sNo, String cCode, String grade, String point) {
        String sql = "update grade set grade = ?, point = ? where sNo = ? and cCode = ?";
        return update(sql, grade, point, sNo, cCode);
    }

    @Override
    public List<Grade> queryForCompletedCourseStudentForTeacher(String cName, String tNo) {
        String sql = "select distinct `sNo`, `grade`, `point` " +
                "from grade inner join course on grade.cCode=course.cCode " +
                "where cName = ? and grade is not null and course.tNo = ?";
        return queryForList(Grade.class, sql, cName, tNo);
    }

    @Override
    public List<User> queryForSelectedCourseStudentForTeacher(String cName, String tNo) {
        String sql = "select distinct student.`no`, student.`name` from " +
                "course inner join grade on course.cCode=grade.cCode " +
                "inner join student on student.no=grade.sNo where grade is null and " +
                "cName = ? and grade.tNo = ?";
        return queryForList(User.class, sql, cName, tNo);
    }
}
