package pers.joy.web;

import com.google.gson.Gson;
import pers.joy.entity.Course;
import pers.joy.entity.User;
import pers.joy.service.CourseService;
import pers.joy.service.StudentService;
import pers.joy.service.impl.CourseServiceImpl;
import pers.joy.service.impl.StudentServiceImpl;
import pers.joy.utils.CookieUtils;

import javax.servlet.http.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class CommonServlet extends BaseServlet {
    private final CourseService courseService = new CourseServiceImpl();
    private final StudentService studentService = new StudentServiceImpl();
    private final Gson gson = new Gson();
    private final static int maxAge = 60 * 60 * 24 * 7;

    /**
     * search course by course code
     */
    protected void searchCourse(HttpServletRequest request, HttpServletResponse resp, Map<String, String> map) {
        String cCode = request.getParameter("courseCode");
        int pageNum = Integer.parseInt(request.getParameter("pageNum"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        List<Course> courses = courseService.searchByCode(cCode, pageNum, pageSize);
        map.put("dataCourse", gson.toJson(courses));
        if (pageNum == 1) {
            map.put("pageCount", courseService.getCourseSumByCCode(cCode));
        }
    }

    /**
     * Sign In
     */
    protected void signIn(HttpServletRequest request, HttpServletResponse resp, Map<String, String> map) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String type = request.getParameter("type");

        Class<?> serviceClass = null;
        try {
            serviceClass = Class.forName(String.format("pers.joy.service.impl.%sServiceImpl", type));
        } catch (ClassNotFoundException e) {
            System.out.println("Cannot find Class " + type + "ServiceImpl");
        }

        User user = null;
        try {
            assert serviceClass != null;
            Constructor<?> con = serviceClass.getConstructor();
            Method method = serviceClass.getDeclaredMethod("signIn", User.class);
            user = (User) method.invoke(con.newInstance(), new User(null, username, password));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (user == null) {
            map.put("status", "fail");
        } else {
            map.put("status", "success");
            map.put("no", user.getNo());
            map.put("name", user.getName());
            // set cookies for user sign in
            CookieUtils.createCookie(resp, "username", username, "localhost", maxAge);
            CookieUtils.createCookie(resp, "type", type, "localhost", maxAge);

            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("type", type);
        }
    }

    /**
     * Sign out
     */
    protected void signOut(HttpServletRequest request, HttpServletResponse resp, Map<String, String> map) {
        request.getSession().invalidate();
    }
}
