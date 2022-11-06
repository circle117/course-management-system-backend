package pers.joy.web;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BaseServlet extends HttpServlet {
    protected final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter("action");
        Map<String, String> map = new HashMap<>();

        try {
            Method method = this.getClass().getDeclaredMethod(action,
                    HttpServletRequest.class, HttpServletResponse.class, Map.class);
            method.invoke(this, req, resp, map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        resp.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        resp.setContentType("application/json;charset=UTF-8");
        resp.getWriter().write(gson.toJson(map));
    }



}
