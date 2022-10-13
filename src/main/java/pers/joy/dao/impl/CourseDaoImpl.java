package pers.joy.dao.impl;

import com.google.gson.Gson;
import org.jetbrains.annotations.NotNull;
import pers.joy.dao.CourseDao;
import pers.joy.entity.Course;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseDaoImpl extends BaseDao implements CourseDao {

    private final Gson gson = new Gson();
    @Override
    public List<Course> queryCourseByCourseCode(String courseCode) {
        String sql = "select `cCode`, `cName`, `credit`, `cDept`, `tName`, course.`tNo`" +
                "from course inner join teacher on course.tNo = teacher.tNo where cCode like ?";
        courseCode = courseCode+'%';
        return queryForList(Course.class, sql, courseCode);
    }

    @Override
    public List<Course> querySelectedCourses(String sNo) {
        String sql = "select course.`cCode`, `cName`, `credit`, `cDept`, `tName`, course.`tNo`" +
                "from grade inner join course on grade.cCode=course.cCode " +
                "inner join teacher on course.tNo=teacher.tNo " +
                "where sNo = ? and grade is null";
        return queryForList(Course.class, sql, sNo);
    }

    @Override
    public List<Course> queryAllCourse() {
        String sql = "select `cCode`, `cName`, `credit`, `cDept`, `tNo` from course";
        return queryForList(Course.class, sql);
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
    public int updateCourse(Course oldCourse, Course newCourse) {
        String sql = "update course set %s where cCode = ?";
        Map<String, String> map = getChangedValues(oldCourse, newCourse);
        List<String> valuesSql = new ArrayList<>();
        for (Map.Entry<String, String> entry:map.entrySet()) {
            if (entry.getKey().equals("credit") | entry.getValue().equals("null")) {
                valuesSql.add("credit="+entry.getValue());
            } else {
                valuesSql.add(entry.getKey() + "=\"" + entry.getValue() + "\"");
            }
        }
        sql = String.format(sql, String.join(", ", valuesSql));
        return update(sql, newCourse.getCCode());
    }

    @Override
    public List<Course> existCourseCode(String courseCode) {
        String sql = "select * from course where cCode = ?";
        return queryForList(Course.class, sql, courseCode);
    }

    @Override
    public List<Course> existNoTeacherCourse(String courseCode) {
        String sql = "select * from course where cCode = ? and tNo is null";
        return queryForList(Course.class, sql, courseCode);
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
    public int insertTNo(Course courseInfo, List<String> teacherList) {
        String sql = "insert into course values";
        List<String> values = new ArrayList<>();
        List<String> items = new ArrayList<>();
        values.add("(\""+courseInfo.getCCode()+"\"");
        values.add("\""+courseInfo.getCName()+"\"");
        values.add(String.valueOf(courseInfo.getCredit()));
        values.add("\""+courseInfo.getCDept()+"\"");
        values.add("\"%s\")");
        for (String tNo: teacherList) {
            if (existCourseAndTeacher(courseInfo.getCCode(), tNo).size()==0) {
                items.add(String.format(String.join(",", values), tNo));
            }
        }
        if (items.size()==0) {
            return -1;
        }
        sql = sql + String.join(", ", items);
        return update(sql);
    }

    @Override
    public List<Course> existCourseAndTeacher(String courseCode, String tNo) {
        String sql = "select * from course where cCode = ? and tNo = ?";
        return queryForList(Course.class, sql, courseCode, tNo);
    }

    Map<String, String> getChangedValues(Course oldCourse, Course newCourse) {
        Map<String, String> changedValues = new HashMap<>();
        if (newCourse.getCName()==null){
            changedValues.put("cName", "null");
        }else if (oldCourse.getCName()==null | !oldCourse.getCName().equals(newCourse.getCName())) {
            changedValues.put("cName", newCourse.getCName());
        }
        if (newCourse.getCredit()==0){
            changedValues.put("cName", "null");
        }else if (oldCourse.getCName()==null | oldCourse.getCredit()!=newCourse.getCredit()) {
            changedValues.put("credit", String.valueOf(newCourse.getCredit()));
        }
        if (newCourse.getCName()==null){
            changedValues.put("cName", "null");
        }else if (oldCourse.getCName()==null | !oldCourse.getCDept().equals(newCourse.getCDept())) {
            changedValues.put("cDept", newCourse.getCDept());
        }
        return changedValues;
    }

}
