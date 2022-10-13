package pers.joy.dao.impl;

import pers.joy.dao.GradeDao;
import pers.joy.entity.SelectCourse;

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
}
