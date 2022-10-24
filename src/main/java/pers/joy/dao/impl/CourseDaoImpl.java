package pers.joy.dao.impl;

import com.google.gson.Gson;
import pers.joy.dao.CourseDao;
import pers.joy.entity.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CourseDaoImpl extends BaseDao implements CourseDao {

    @Override
    public List<Course> queryCourseByCourseCode(String courseCode, int begin, int pageSize) {
        String sql = "select `cCode`, `cName`, `credit`, `cDept`, `name` as `tName`, course.`tNo`" +
                "from course inner join teacher on course.tNo = teacher.no where cCode like ? " +
                "order by cCode limit ?, ?";
        courseCode = "%"+courseCode+"%";
        return queryForList(Course.class, sql, courseCode, begin, pageSize);
    }

    @Override
    public String queryCourseSum(String courseCode) {
        String sql = "select count(*) from course where cCode like ?";
        courseCode = "%"+courseCode+"%";
        return String.valueOf(queryForSingleValue(sql, courseCode));
    }

    @Override
    public List<Course> querySelectedCoursesBySNo(String sNo, int begin, int pageSize) {
        String sql = "select course.`cCode`, `cName`, `credit`, `cDept`, `name` as `tName`, course.`tNo`" +
                "from grade inner join course on grade.cCode=course.cCode and grade.tNo=course.tNo " +
                "inner join teacher on course.tNo=teacher.no " +
                "where sNo = ? and grade is null order by course.cCode limit ?, ?";
        return queryForList(Course.class, sql, sNo, begin, pageSize);
    }

    @Override
    public List<Course> queryAllCourse() {
        String sql = "select `cCode`, `cName`, `credit`, `cDept`, `tNo` from course order by cCode, tNo";
        return queryForList(Course.class, sql);
    }

    @Override
    public List<Object> queryCourseName() {
        String sql = "select distinct `cName` from course order by cName";
        return queryForColumnList(sql);
    }

    @Override
    public int insertCourse(Course course) {
        String sql = "insert into course values(?, ?, ?, ?, ?)";
        return update(sql,
                course.getCCode(), course.getCName(), course.getCredit(), course.getCDept(), course.getTNo());
    }

    @Override
    public int deleteCourseByCCode(Course course) {
        String sql = "delete from course where cCode = ? and tNo ";
        if (course.getTNo()==null) {
            sql += "is null";
            return update(sql, course.getCCode());
        } else {
            sql += "= ?";
            return update(sql, course.getCCode(), course.getTNo());
        }

    }

    @Override
    public int updateCourse(Course course) {
        String sql = "update course set cName=?, credit=?, cDept=? where cCode = ?";
        return update(sql,
                course.getCName(), course.getCredit(), course.getCDept(), course.getCCode());
    }

    @Override
    public List<Course> existCourseCode(String courseCode) {
        String sql = "select * from course where cCode = ?";
        return queryForList(Course.class, sql, courseCode);
    }

    @Override
    public Course existNoTeacherCourse(String courseCode) {
        String sql = "select * from course where cCode = ? and tNo is null";
        return queryForOne(Course.class, sql, courseCode);
    }

    @Override
    public int updateTNoForExistItem(String courseCode, String tNo) {
        String sql = "update course set tNo = ? where cCode = ?";
        return update(sql, tNo, courseCode);
    }

    @Override
    public Course queryCourseInfoByCCode(String courseCode) {
        String sql = "select cCode, cName, credit, cDept from course where cCode = ?";
        return queryForOne(Course.class, sql, courseCode);
    }

    @Override
    public Course queryCourseByCCodeAndTNo(String courseCode, String tNo) {
        String sql = "select * from course where cCode = ? and tNo = ?";
        return queryForOne(Course.class, sql, courseCode, tNo);
    }

    @Override
    public String queryCCodeByName(String cName) {
        String sql = "select `cCode` from course where cName = ?";
        return (String) queryForSingleValue(sql, cName);
    }

    @Override
    public List<Object> queryCourseNameListForTeacher(String tNo) {
        String sql = "select `cName` from course where tNo = ? order by cName";
        return queryForColumnList(sql, tNo);
    }
}
