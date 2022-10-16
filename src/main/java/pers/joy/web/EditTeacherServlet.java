package pers.joy.web;

import com.google.gson.Gson;
import pers.joy.service.AdministratorService;
import pers.joy.service.impl.AdministratorServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EditTeacherServlet extends HttpServlet {

    private final AdministratorService administratorService = new AdministratorServiceImpl();
    private final Gson gson = new Gson();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tNo = request.getParameter("tNo");
        Map<String, String > editTeacher = new HashMap<>();
        editTeacher = gson.fromJson(request.getParameter("editTeacher"), editTeacher.getClass());

        Map<String, String> map = new HashMap<>();
        int res = administratorService.editTeacher(tNo, editTeacher);
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
