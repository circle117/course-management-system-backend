package pers.joy.web;

import com.google.gson.Gson;
import pers.joy.entity.SelectCourse;
import pers.joy.service.StudentService;
import pers.joy.service.impl.StudentServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetCompletedCoursesServlet extends HttpServlet {
    private final StudentService studentService = new StudentServiceImpl();
    private final Gson gson = new Gson();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sNo = request.getParameter("sNo");

        Map<String, String> map = new HashMap<>();
        List<SelectCourse> selectCourseList = studentService.getCompletedCourses(sNo);
        map.put("completedCourses", gson.toJson(selectCourseList));
        float gpa = studentService.getGPA(selectCourseList);
        map.put("GPA", String.valueOf(gpa));

        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(gson.toJson(map));
    }
}
