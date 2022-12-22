package pers.joy.controller;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pers.joy.entity.Course;
import pers.joy.entity.User;
import pers.joy.service.AdministratorService;
import pers.joy.service.CourseService;
import pers.joy.service.StudentService;
import pers.joy.service.TeacherService;
import pers.joy.utils.CookieUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CommonController {

    private final StudentService studentService;
    private final TeacherService teacherService;
    private final AdministratorService administratorService;
    private final CourseService courseService;
    private final static int maxAge = 60 * 60 * 24 * 7;
    private final Gson gson = new Gson();

    public CommonController(StudentService studentService, TeacherService teacherService,
                            AdministratorService administratorService, CourseService courseService) {
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.administratorService = administratorService;
        this.courseService = courseService;
    }

    @GetMapping("/signIn/{username}/{password}/{type}")
    public String signIn(HttpServletResponse resp,
                         HttpSession session,
                         @PathVariable("username") String username,
                         @PathVariable("password") String password,
                         @PathVariable("type") String type) {
        User user = null;
        if ("Student".equals(type)) {
            user = studentService.signIn(new User(null, username, password));
        } else if ("Teacher".equals(type)) {
            user = teacherService.signIn(new User(null, username, password));
        } else if ("Administrator".equals(type)) {
            user = administratorService.signIn(new User(null, username, password));
        }

        Map<String, String> map = new HashMap<>();
        if (user == null) {
            map.put("status", "fail");
        } else {
            CookieUtils.createCookie(resp, "username", username, "localhost", maxAge);
            CookieUtils.createCookie(resp, "type", type, "localhost", maxAge);
            session.setAttribute("user", user);
            session.setAttribute("type", type);

            map.put("status", "success");
            map.put("no", user.getNo());
            map.put("name", user.getName());
        }

        return gson.toJson(map);
    }

    @GetMapping("/signOut")
    public void signOut(HttpSession session) {
        session.invalidate();
    }

    @GetMapping("/course/{courseCode}/{pageNum}/{pageSize}")
    public String getSearchedCourse(@PathVariable("courseCode") String courseCode,
                                    @PathVariable("pageNum") int pageNum,
                                    @PathVariable("pageSize") int pageSize) {
        List<Course> courses = courseService.searchByCode(courseCode, pageNum, pageSize);
        return gson.toJson(courses);
    }

    @GetMapping("/courseNum/{courseCode}")
    public String getSearchedCourseNum(@PathVariable("courseCode") String courseCode) {
        return courseService.getCourseSumByCCode(courseCode);
    }
}
