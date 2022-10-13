package pers.joy.web;

import com.google.gson.Gson;
import pers.joy.entity.User;
import pers.joy.service.StudentService;
import pers.joy.service.UserService;
import pers.joy.service.impl.StudentServiceImpl;
import pers.joy.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SignInServlet extends HttpServlet {

    private final UserService userService = new UserServiceImpl();
    private final StudentService studentService = new StudentServiceImpl();
    private final Gson gson = new Gson();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String type = request.getParameter("type");

        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");
        response.setContentType("application/json;charset=UTF-8");
        Map<String, String> map = new HashMap<>();
        User user = userService.signIn(type, new User(null, username, password));
        if (user == null) {
            map.put("signInStatus", "fail");
        } else if (type.equals("student")) {
            map.put("signInStatus", "success");
            map.put("no", user.getNo());
            map.put("name", user.getName());
            map.put("selectedCourses", gson.toJson(studentService.getSelectedCourses(user.getNo())));
        } else if (type.equals("administrator")) {
            map.put("signInStatus", "success");
            map.put("no", user.getNo());
            map.put("name", user.getName());
        }
        response.getWriter().write(gson.toJson(map));
    }
}
