package pers.joy.web;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import pers.joy.dao.CourseDao;
import pers.joy.dao.GradeDao;
import pers.joy.dao.StudentDao;
import pers.joy.entity.Course;
import pers.joy.entity.Grade;
import pers.joy.entity.User;
import pers.joy.service.CourseService;
import pers.joy.service.GradeService;
import pers.joy.service.StudentService;
import pers.joy.service.impl.CourseServiceImpl;
import pers.joy.service.impl.GradeServiceImpl;
import pers.joy.service.impl.StudentServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentServlet extends BaseServlet{

    private final StudentService studentService = new StudentServiceImpl();
    private final CourseService courseService = new CourseServiceImpl();
    private final GradeService gradeService = new GradeServiceImpl();
    private final Gson gson = new Gson();

    /**
     * student sign in
     */
    protected void signIn(HttpServletRequest request, Map<String, String> map) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = studentService.signIn(new User(null, username, password));
        if (user == null) {
            map.put("status", "fail");
        } else {
            map.put("status", "success");
            map.put("no", user.getNo());
            map.put("name", user.getName());
        }
    }

    /**
     * search course by course code
     */
    protected void searchCourse(HttpServletRequest request, Map<String, String> map) {
        String cCode = request.getParameter("courseCode");
        int pageNum = Integer.parseInt(request.getParameter("pageNum"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        List<Course> courses = courseService.searchByCode(cCode, pageNum, pageSize);
        map.put("dataCourse", gson.toJson(courses));
        map.put("pageCount", courseService.getCourseSum(cCode));
    }

    /**
     * select courses
     * must sign in
     * one student can only choose one course(course code) once.
     */
    protected void selectCourse(HttpServletRequest request, Map<String, String> map) {
        String sNo = request.getParameter("sNo");
        String jsonData = request.getParameter("course");
        JsonArray jsonArray = new JsonParser().parse(jsonData).getAsJsonArray();
        List<Grade> gradeList = new ArrayList<>();
        for (JsonElement jsonElement:jsonArray) {
            Grade grade = gson.fromJson(jsonElement, Grade.class);
            grade.setSNo(sNo);
            gradeList.add(grade);
        }

        if (sNo==null) {
            map.put("Status", "NotSignIn");
        } else {
            List<String> res = gradeService.selectCourse(gradeList);
            if (res.size() == 0) {
                map.put("Status", "Success");
            } else {
                map.put("Status", "AlreadySelected");
                map.put("Courses", gson.toJson(res));
            }
        }
    }

    /**
     * get completed courses and GPA by student No
     */
    protected void completedCourse(HttpServletRequest request, Map<String, String> map) {
        String sNo = request.getParameter("sNo");
        List<Grade> gradeList = gradeService.getCompletedCourses(sNo);
        map.put("completedCourses", gson.toJson(gradeList));
        float gpa = getGPA(gradeList);
        map.put("GPA", String.valueOf(Math.round(gpa*100)/100.0));
    }

    /**
     * get selected courses by student No
     */
    protected void selectedCourse(HttpServletRequest request, Map<String, String> map) {
        String sNo = request.getParameter("sNo");
        int pageNum = Integer.parseInt(request.getParameter("pageNum"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        map.put("selectedCourses", gson.toJson(courseService.getSelectedCoursesInfo(sNo, pageNum, pageSize)));
        map.put("pageCount", gradeService.getSelectedCourseSum(sNo));
    }

    /**
     * drop course by student No, course code and teacher No
     */
    protected void dropCourse(HttpServletRequest request, Map<String, String> map) {
        String sNo = request.getParameter("sNo");
        String jsonData = request.getParameter("dropCourse");
        JsonArray jsonArray = new JsonParser().parse(jsonData).getAsJsonArray();
        List<Grade> gradeList = new ArrayList<>();
        for (JsonElement jsonElement:jsonArray) {
            Grade grade = gson.fromJson(jsonElement, Grade.class);
            grade.setSNo(sNo);
            gradeList.add(grade);
        }

        gradeService.dropCourse(gradeList);
        map.put("Status", "Success");
    }

    protected float getGPA(List<Grade> gradeList) {
        int gradeSum = 0;
        int creditSum = 0;
        for(Grade c: gradeList) {
            gradeSum += c.getGrade()*c.getCredit();
            creditSum += c.getCredit();
        }
        return (float) gradeSum/creditSum;
    }
}
