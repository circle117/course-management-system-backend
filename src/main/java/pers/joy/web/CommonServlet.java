package pers.joy.web;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import pers.joy.entity.Course;
import pers.joy.entity.User;
import pers.joy.service.CourseService;
import pers.joy.utils.CookieUtils;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class CommonServlet extends BaseServlet {

    private CourseService courseService;
    private ApplicationContext context;
    private final static int maxAge = 60 * 60 * 24 * 7;

    @Override
    public void init() throws ServletException {
        super.init();
        context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        assert context != null;
        courseService = context.getBean(CourseService.class);
    }

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
            Method method = serviceClass.getDeclaredMethod("signIn", User.class);
            user = (User) method.invoke(context.getBean(serviceClass), new User(null, username, password));
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
