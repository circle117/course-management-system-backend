package pers.joy.dao.impl;

import pers.joy.dao.GradeDao;
import pers.joy.entity.SelectCourse;
import pers.joy.entity.User;

import java.util.ArrayList;
import java.util.List;

public class GradeDaoImpl extends BaseDao implements GradeDao {
    @Override
    public List<String> insertSelectCourse(List<SelectCourse> selectCourseList) {
        String sql = "insert into grade(sNo, cCode, tNo) values(?, ?, ?)";
        List<String> failedSelectCourseList = new ArrayList<>();
        for (SelectCourse selectCourse:selectCourseList) {
            int res = update(sql, selectCourse.getSNo(), selectCourse.getCCode(), selectCourse.getTNo());
            if (res<0) {
                failedSelectCourseList.add(selectCourse.getCCode());
            }
        }
        return failedSelectCourseList;
    }

    @Override
    public void deleteCourse(List<SelectCourse> selectCourseList) {
        String sql = "delete from grade where sNo = ? and cCode = ? and tNo = ?";
        for (SelectCourse selectCourse: selectCourseList) {
            update(sql, selectCourse.getSNo(), selectCourse.getCCode(), selectCourse.getTNo());
        }
    }

    @Override
    public List<SelectCourse> queryForCompletedCourses(String sNo) {
        String sql = "select distinct `sNo`, grade.`cCode`, `grade`, `point`, course.`cName`, `credit` " +
                "from grade left join course on grade.cCode=course.cCode " +
                "where sNo = ? and grade is not null";
        return queryForList(SelectCourse.class, sql, sNo);
    }

    @Override
    public List<SelectCourse> queryForCompletedCourseStudent(String cName) {
        String sql = "select distinct `sNo`, `grade`, `point` " +
                "from grade inner join course on grade.cCode=course.cCode " +
                "where cName = ? and grade is not null";
        return queryForList(SelectCourse.class, sql, cName);
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
    public List<SelectCourse> queryForCompletedCourseStudentForTeacher(String cName, String tNo) {
        String sql = "select distinct `sNo`, `grade`, `point` " +
                "from grade inner join course on grade.cCode=course.cCode " +
                "where cName = ? and grade is not null and course.tNo = ?";
        return queryForList(SelectCourse.class, sql, cName, tNo);
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
