package pers.joy.web;

import com.google.gson.Gson;
import pers.joy.service.StudentService;
import pers.joy.service.impl.StudentServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UpdateSelectedServlet extends HttpServlet {

    private final StudentService studentService = new StudentServiceImpl();
    private final Gson gson = new Gson();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sNo = request.getParameter("sNo");

        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");
        response.setContentType("application/json;charset=UTF-8");

        Map<String, String> map = new HashMap<>();
        map.put("selectedCourses", gson.toJson(studentService.getSelectedCourses(sNo)));

        response.getWriter().write(gson.toJson(map));
    }

}
