package pers.joy.web;

import com.google.gson.Gson;
import pers.joy.service.TeacherService;
import pers.joy.service.impl.TeacherServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetCompletedCourseStudentForTeacherServlet extends HttpServlet {

    private final TeacherService teacherService = new TeacherServiceImpl();
    private final Gson gson = new Gson();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cName = request.getParameter("cName");
        String tNo = request.getParameter("tNo");
        Map<String, String> map = new HashMap<>();

        map.put("completedCourseStudent", gson.toJson(teacherService.getCompletedCourseStudentForTeacher(cName, tNo)));

        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(gson.toJson(map));
    }
}
