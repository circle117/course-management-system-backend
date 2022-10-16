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

public class EditCourseServlet extends HttpServlet {

    private final AdministratorService administratorService = new AdministratorServiceImpl();
    private final Gson gson = new Gson();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cCode = request.getParameter("cCode");
        Map<String, String> editCourse = new HashMap<>();
        editCourse = gson.fromJson(request.getParameter("editCourse"), editCourse.getClass());

        Map<String, String> map = new HashMap<>();
        int res = administratorService.editCourse(cCode, editCourse);
        if (res>=0) {
            map.put("Status", "success");
        } else {
            map.put("Status", "fail");
        }

        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(gson.toJson(map));
    }

}
