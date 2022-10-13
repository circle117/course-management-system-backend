package pers.joy.web;

import com.google.gson.Gson;
import pers.joy.entity.Course;
import pers.joy.service.AdministratorService;
import pers.joy.service.impl.AdministratorServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CreateCourseServlet extends HttpServlet {

    private final AdministratorService administratorService = new AdministratorServiceImpl();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> course = new HashMap<>();
        course = gson.fromJson(request.getParameter("newCourse"), course.getClass());

        Map<String, String> map = new HashMap<>();
        int res = administratorService.createCourse(course);
        if (res>0) {
            map.put("Status", "success");
            map.put("cCode", course.get("cCode"));
        } else {
            map.put("Status", "fail");
            map.put("cCode", course.get("cCode"));
        }

        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(gson.toJson(map));
    }

}
