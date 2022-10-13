package pers.joy.web;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import pers.joy.entity.Course;
import pers.joy.service.StudentService;
import pers.joy.service.impl.StudentServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DropCourseServlet extends HttpServlet {

    private final StudentService studentService = new StudentServiceImpl();
    private final Gson gson = new Gson();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sNo = request.getParameter("sNo");
        String jsonData = request.getParameter("dropCourse");
        JsonArray jsonArray = new JsonParser().parse(jsonData).getAsJsonArray();
        List<Course> courseList = new ArrayList<>();
        for (JsonElement jsonElement:jsonArray) {
            courseList.add(gson.fromJson(jsonElement, Course.class));
        }

        studentService.dropCourse(sNo, courseList);

        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");
        response.setContentType("application/json;charset=UTF-8");
        Map<String, String> map = new HashMap<>();
        map.put("Status", "Success");
        response.getWriter().write(gson.toJson(map));
    }
}
