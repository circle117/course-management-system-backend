package pers.joy.dao.impl;

import com.google.gson.Gson;
import pers.joy.dao.CourseDao;
import pers.joy.entity.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CourseDaoImpl extends BaseDao implements CourseDao {

    private final Gson gson = new Gson();
    @Override
    public List<Course> queryCourseByCourseCode(String courseCode) {
        String sql = "select `cCode`, `cName`, `credit`, `cDept`, `name` as `tName`, course.`tNo`" +
                "from course inner join teacher on course.tNo = teacher.no where cCode like ? " +
                "order by cCode";
        courseCode = courseCode+'%';
        return queryForList(Course.class, sql, courseCode);
    }

    @Override
    public List<Course> querySelectedCourses(String sNo) {
        String sql = "select course.`cCode`, `cName`, `credit`, `cDept`, `name` as `tName`, course.`tNo`" +
                "from grade inner join course on grade.cCode=course.cCode and grade.tNo=course.tNo " +
                "inner join teacher on course.tNo=teacher.no " +
                "where sNo = ? and grade is null order by course.cCode";
        return queryForList(Course.class, sql, sNo);
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
    public int insertCourse(Map<String, String> course) {
        String sql = "insert into course(%s) values(%s)";
        List<String> items = new ArrayList<>();
        List<String> values = new ArrayList<>();
        for (Map.Entry<String, String> entry: course.entrySet()) {
            if (entry.getValue()==null) {
                continue;
            }
            items.add(entry.getKey());
            if (entry.getKey().equals("credit")) {
                values.add(entry.getValue());
            } else{
                values.add("\"" + entry.getValue() + "\"");
            }
        }
        return update(String.format(sql,
                String.join(", ", items),
                String.join(", ", values)));
    }

    @Override
    public int deleteCourse(Course course) {
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
    public int updateCourse(String cCode, Map<String, String> editCourse) {
        String sql = "update course set %s where cCode = ?";
        List<String> values = new ArrayList<>();
        for (Map.Entry<String, String> entry: editCourse.entrySet()) {
            if(entry.getKey().equals("credit")) {
                values.add("credit="+entry.getValue());
            } else {
                values.add(entry.getKey()+"=\""+entry.getValue()+"\"");
            }
        }
        sql = String.format(sql, String.join(", ", values));
        return update(sql, cCode);
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
    public Course queryCourseInfo(String courseCode) {
        String sql = "select cCode, cName, credit, cDept from course where cCode = ?";
        return queryForOne(Course.class, sql, courseCode);
    }

    @Override
    public List<String> insertTNo(Course courseInfo, List<String> teacherList) {
        List<String> failTeacherNo = new ArrayList<>();
        String sql = "insert into course values";
        List<String> values = new ArrayList<>();
        List<String> items = new ArrayList<>();
        values.add("(\""+courseInfo.getCCode()+"\"");
        values.add("\""+courseInfo.getCName()+"\"");
        values.add(String.valueOf(courseInfo.getCredit()));
        values.add("\""+courseInfo.getCDept()+"\"");
        values.add("\"%s\")");
        for (String tNo: teacherList) {
            if (existCourseAndTeacher(courseInfo.getCCode(), tNo) == null) {
                items.add(String.format(String.join(",", values), tNo));
            } else {
                failTeacherNo.add(tNo);
            }
        }
        if (items.size()==0) {
            return failTeacherNo;
        }
        sql = sql + String.join(", ", items);
        update(sql);
        return failTeacherNo;
    }

    @Override
    public Course existCourseAndTeacher(String courseCode, String tNo) {
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
