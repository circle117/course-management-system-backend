package pers.joy.web;

import com.google.gson.Gson;
import pers.joy.service.AdministratorService;
import pers.joy.service.impl.AdministratorServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class InputGradeServlet extends HttpServlet {

    private final AdministratorService administratorService = new AdministratorServiceImpl();
    private final Gson gson = new Gson();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sNo = request.getParameter("sNo");
        String cName = request.getParameter("cName");
        String grade = request.getParameter("grade");

        Map<String, String> map = new HashMap<>();
        int res = administratorService.inputGrade(sNo, cName, grade);
        if (res>0) {
            map.put("status", "success");
        } else {
            map.put("status", "fail");
        }

        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(gson.toJson(map));

    }
}
