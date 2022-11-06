package pers.joy.web;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import pers.joy.service.CourseService;
import pers.joy.service.GradeService;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.util.List;
import java.util.Map;

public class TeacherServlet extends BaseServlet {

    private GradeService gradeService;
    private CourseService courseService;

    @Override
    public void init() throws ServletException {
        super.init();
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        assert context != null;
        gradeService = context.getBean(GradeService.class);
        courseService = context.getBean(CourseService.class);
    }

    /**
     * get course name list by teacher No
     */
    protected void courseNameList(HttpServletRequest request, HttpServletResponse resp, Map<String, String> map) {
        String tNo = request.getParameter("no");
        List<String> courseNameList = courseService.getCourseNameList(tNo);

        map.put("courseNameList", gson.toJson(courseNameList));
    }


    /**
     * get a list of student who completed the course by course code and teacher No
     */
    protected void completedCourseStudent(HttpServletRequest request, HttpServletResponse resp, Map<String, String> map) {
        String cName = request.getParameter("cName");
        String tNo = request.getParameter("tNo");

        map.put("completedCourseStudent", gson.toJson(gradeService.getCompletedCourseStudent(cName, tNo)));
    }

    /**
     * get a list of student who is taking the course by course code and teacher No
     */
    protected void selectedCourseStudent(HttpServletRequest request, HttpServletResponse resp, Map<String, String> map) {
        String cName = request.getParameter("cName");
        String tNo = request.getParameter("tNo");

        map.put("selectedCourseStudent", gson.toJson(gradeService.getSelectedCourseStudent(cName, tNo)));
    }

    protected void submitGrade(HttpServletRequest request, HttpServletResponse resp, Map<String, String> map) {
        String sNo = request.getParameter("sNo");
        String cName = request.getParameter("cName");
        String grade = request.getParameter("grade");

        int res = gradeService.submitGrade(sNo, cName, grade);
        if (res>0) {
            map.put("status", "success");
        } else {
            map.put("status", "fail");
        }
    }
}
