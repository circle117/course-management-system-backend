package pers.joy.web;

import com.google.gson.Gson;
import pers.joy.entity.Course;
import pers.joy.service.CourseService;
import pers.joy.service.impl.CourseServiceImpl;

import javax.servlet.http.*;
import java.util.List;
import java.util.Map;

public class CommonServlet extends BaseServlet{
    private final CourseService courseService = new CourseServiceImpl();
    private final Gson gson = new Gson();

    /**
     * search course by course code
     */
    protected void searchCourse(HttpServletRequest request, HttpServletResponse resp, Map<String, String> map) {
        String cCode = request.getParameter("courseCode");
        int pageNum = Integer.parseInt(request.getParameter("pageNum"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        List<Course> courses = courseService.searchByCode(cCode, pageNum, pageSize);
        map.put("dataCourse", gson.toJson(courses));
        if (pageNum==1) {
            map.put("pageCount", courseService.getCourseSumByCCode(cCode));
        }
    }

    /**
     * search teacher by teacher No
     */
}
