package pers.joy.web;

import com.google.gson.Gson;
import pers.joy.entity.Course;
import pers.joy.service.CourseService;
import pers.joy.service.impl.CourseServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchCourseServlet extends HttpServlet {

    private final CourseService courseService = new CourseServiceImpl();
    private final Gson gson = new Gson();
    private List<Course> courses = new ArrayList<Course>();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cCode = request.getParameter("courseCode");

        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");
        response.setContentType("application/json;charset=UTF-8");

        Map<String, List<Course>> map = new HashMap<String, List<Course>>();
        courses = courseService.searchByCode(cCode);
        map.put("dataCourse", courses);
        response.getWriter().write(gson.toJson(map));
    }
}
