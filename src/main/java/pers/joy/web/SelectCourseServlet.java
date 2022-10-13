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

public class SelectCourseServlet extends HttpServlet {
    private final Gson gson = new Gson();
    private StudentService studentService = new StudentServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sNo = request.getParameter("sNo");
        String jsonData = request.getParameter("course");
        JsonArray jsonArray = new JsonParser().parse(jsonData).getAsJsonArray();
        List<Course> courseList = new ArrayList<>();
        for (JsonElement jsonElement:jsonArray) {
            courseList.add(gson.fromJson(jsonElement, Course.class));
        }

        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");
        response.setContentType("application/json;charset=UTF-8");
        Map<String, String> map = new HashMap<>();
        if (sNo==null) {
            map.put("Status", "NotSignIn");
            response.getWriter().write(gson.toJson(map));
            return;
        }
        List<String> res = studentService.selectCourse(sNo, courseList);
        if (res.size()==0) {
            map.put("Status", "Success");
        }
        else {
            map.put("Status", "AlreadySelected");
            map.put("Courses", gson.toJson(res));
        }
        response.getWriter().write(gson.toJson(map));
    }

}
