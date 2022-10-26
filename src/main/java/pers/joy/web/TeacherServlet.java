package pers.joy.web;

import com.google.gson.Gson;
import pers.joy.entity.User;
import pers.joy.service.CourseService;
import pers.joy.service.GradeService;
import pers.joy.service.TeacherService;
import pers.joy.service.impl.CourseServiceImpl;
import pers.joy.service.impl.GradeServiceImpl;
import pers.joy.service.impl.TeacherServiceImpl;

import javax.servlet.http.*;
import java.util.List;
import java.util.Map;

public class TeacherServlet extends BaseServlet {

    private final TeacherService teacherService = new TeacherServiceImpl();
    private final GradeService gradeService = new GradeServiceImpl();
    private final CourseService courseService = new CourseServiceImpl();
    private final Gson gson = new Gson();

    /**
     * teacher sign in
     */
    protected void signIn(HttpServletRequest request, HttpServletResponse resp, Map<String, String> map) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = teacherService.signIn(new User(null, username, password));
        if (user == null) {
            map.put("status", "fail");
        } else {
            map.put("status", "success");
            map.put("no", user.getNo());
            map.put("name", user.getName());
        }
    }

    /**
     * get course name list by teacher No
     */
    protected void courseNameList(HttpServletRequest request, HttpServletResponse resp, Map<String, String> map) {
        String tNo = request.getParameter("no");
        List<Object> courseNameList = courseService.getCourseNameList(tNo);

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
