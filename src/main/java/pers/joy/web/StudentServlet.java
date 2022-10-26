package pers.joy.web;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import pers.joy.entity.Grade;
import pers.joy.entity.User;
import pers.joy.service.CourseService;
import pers.joy.service.GradeService;
import pers.joy.service.StudentService;
import pers.joy.service.impl.CourseServiceImpl;
import pers.joy.service.impl.GradeServiceImpl;
import pers.joy.service.impl.StudentServiceImpl;

import javax.servlet.http.*;
import java.util.ArrayList;
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
    protected void signIn(HttpServletRequest request, HttpServletResponse resp, Map<String, String> map) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = studentService.signIn(new User(null, username, password));
        if (user == null) {
            map.put("status", "fail");
        } else {
            map.put("status", "success");
            map.put("no", user.getNo());
            map.put("name", user.getName());
            // HttpSession session = request.getSession();
            // session.setAttribute("no", user.getNo());
            Cookie cookie = new Cookie("username", username);
            cookie.setDomain("localhost");
            cookie.setMaxAge(60*60*24*7);
            resp.addCookie(cookie);
            Cookie cookie1 = new Cookie("type", "student");
            cookie1.setDomain("localhost");
            cookie1.setMaxAge(60*60*24*7);
            resp.addCookie(cookie1);
        }
    }

    /**
     * select courses
     * must sign in
     * one student can only choose one course(course code) once.
     */
    protected void selectCourse(HttpServletRequest request, HttpServletResponse resp, Map<String, String> map) {
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
    protected void completedCourse(HttpServletRequest request, HttpServletResponse resp, Map<String, String> map) {
        String sNo = request.getParameter("sNo");
        List<Grade> gradeList = gradeService.getCompletedCourses(sNo);
        map.put("completedCourses", gson.toJson(gradeList));
        float gpa = getGPA(gradeList);
        map.put("GPA", String.valueOf(Math.round(gpa*100)/100.0));
    }

    /**
     * get selected courses by student No
     */
    protected void selectedCourse(HttpServletRequest request, HttpServletResponse resp, Map<String, String> map) {
        String sNo = request.getParameter("sNo");
        int pageNum = Integer.parseInt(request.getParameter("pageNum"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        map.put("selectedCourses", gson.toJson(courseService.getSelectedCoursesInfo(sNo, pageNum, pageSize)));
        if (pageNum==1) {
            map.put("pageCount", gradeService.getSelectedCourseSum(sNo));
        }
    }

    /**
     * drop course by student No, course code and teacher No
     */
    protected void dropCourse(HttpServletRequest request, HttpServletResponse resp, Map<String, String> map) {
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
