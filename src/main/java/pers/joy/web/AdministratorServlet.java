package pers.joy.web;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import pers.joy.entity.Course;
import pers.joy.entity.User;
import pers.joy.service.*;
import pers.joy.service.impl.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AdministratorServlet extends BaseServlet {

    private final AdministratorService administratorService = new AdministratorServiceImpl();
    private final TeacherService teacherService = new TeacherServiceImpl();
    private final StudentService studentService = new StudentServiceImpl();
    private final GradeService gradeService = new GradeServiceImpl();
    private final CourseService courseService = new CourseServiceImpl();
    private final Gson gson = new Gson();

    protected void signIn(HttpServletRequest request, Map<String, String> map) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = administratorService.signIn(new User(null, username, password));
        if (user == null) {
            map.put("status", "fail");
        } else {
            map.put("status", "success");
            map.put("no", user.getNo());
            map.put("name", user.getName());
        }
    }

    /**
     * grade management
     */
    protected void getAllCourseNameList(HttpServletRequest request, Map<String, String> map) {
        List<Object> courseNameList = courseService.getAllCourseNameList();
        map.put("courseNameList", gson.toJson(courseNameList));
    }

    protected void completedCourseStudent(HttpServletRequest request, Map<String, String> map) {
        String cName = request.getParameter("cName");
        map.put("completedCourseStudent", gson.toJson(gradeService.getCompletedCourseStudent(cName)));
    }

    protected void selectedCourseStudent(HttpServletRequest request, Map<String, String> map) {
        String cName = request.getParameter("cName");
        map.put("selectedCourseStudent", gson.toJson(gradeService.getSelectedCourseStudent(cName)));
    }

    protected void submitGrade(HttpServletRequest request, Map<String, String> map) {
        String sNo = request.getParameter("sNo");
        String cName = request.getParameter("cName");
        String grade = request.getParameter("grade");

        int res = gradeService.submitGrade(sNo, cName, grade);
        if (res > 0) {
            map.put("status", "success");
        } else {
            map.put("status", "fail");
        }
    }

    /**
     * course management
     */
    protected void getCourseList(HttpServletRequest request, Map<String, String> map) {
        List<Course> courseList = courseService.getCourseList();
        map.put("dataCourse", gson.toJson(courseList));
    }

    protected void createCourse(HttpServletRequest request, Map<String, String> map) {
        Course course = gson.fromJson(request.getParameter("newCourse"), Course.class);
        int res = courseService.createCourse(course);
        if (res > 0) {
            map.put("status", "success");
            map.put("cCode", course.getCCode());
        } else {
            map.put("status", "fail");
            map.put("cCode", course.getCCode());
        }
    }

    protected void addTeacher(HttpServletRequest request, Map<String, String> map) {
        String courseCode = request.getParameter("cCode");
        String jsonData = request.getParameter("teacherList");
        JsonArray jsonArray = new JsonParser().parse(jsonData).getAsJsonArray();
        List<String> teacherList = new LinkedList<>();
        for (JsonElement jsonElement : jsonArray) {
            teacherList.add(gson.fromJson(jsonElement, String.class));
        }

        List<String> failTeacherNo = courseService.addTeacher(courseCode, teacherList);
        if (failTeacherNo.size() == 0) {
            map.put("status", "success");
        } else {
            map.put("status", "fail");
            map.put("failTeacherNo", gson.toJson(failTeacherNo));
        }
    }

    protected void editCourse(HttpServletRequest request, Map<String, String> map) {
        Course course = gson.fromJson(request.getParameter("editCourse"), Course.class);

        int res = courseService.editCourse(course);
        if (res >= 0) {
            map.put("status", "success");
        } else {
            map.put("status", "fail");
        }
    }

    protected void deleteCourse(HttpServletRequest request, Map<String, String> map) {
        Course course = gson.fromJson(request.getParameter("course"), Course.class);

        int res = courseService.deleteCourse(course);
        if (res > 0) {
            map.put("status", "success");
        } else {
            map.put("status", "fail");
        }
    }

    /**
     * student management
     */
    protected void getStudentList(HttpServletRequest request, Map<String, String> map) {
        List<User> userList = studentService.getStudentList();
        map.put("dataStudent", gson.toJson(userList));
    }

    protected void createStudent(HttpServletRequest request, Map<String, String> map) {
        User student = gson.fromJson(request.getParameter("newStudent"), User.class);

        int res = studentService.createStudent(student);
        if (res <= 0) {
            map.put("status", "fail");
        } else {
            map.put("status", "success");
        }
    }

    protected void editStudent(HttpServletRequest request, Map<String, String> map) {
        User student = gson.fromJson(request.getParameter("editStudent"), User.class);

        int res = studentService.editStudent(student);
        if (res > 0) {
            map.put("status", "success");
        } else {
            map.put("status", "fail");
        }
    }

    protected void deleteStudent(HttpServletRequest request, Map<String, String> map) {
        String sNo = request.getParameter("sNo");

        int res = studentService.deleteStudent(sNo);
        if (res>0) {
            map.put("status", "success");
        } else {
            map.put("status", "fail");
        }

    }

    /**
     * teacher management
     */
    protected void getTeacherList(HttpServletRequest request, Map<String, String> map) {
        List<User> teacherList = teacherService.getTeacherList();
        map.put("dataTeacher", gson.toJson(teacherList));
    }

    protected void createTeacher(HttpServletRequest request, Map<String, String> map) {
        User teacher = gson.fromJson(request.getParameter("newTeacher"), User.class);

        int res = teacherService.createTeacher(teacher);
        if (res > 0) {
            map.put("status", "success");
        } else {
            map.put("status", "fail");
        }
    }

    protected void editTeacher(HttpServletRequest request, Map<String, String> map) {
        User teacher = gson.fromJson(request.getParameter("editTeacher"), User.class);

        int res = teacherService.editTeacher(teacher);
        if (res > 0) {
            map.put("status", "success");
        } else {
            map.put("status", "fail");
        }
    }

    protected void deleteTeacher(HttpServletRequest request, Map<String, String> map) {
        String tNo = request.getParameter("tNo");

        int res = teacherService.deleteTeacher(tNo);
        if (res>0) {
            map.put("status", "success");
        } else {
            map.put("status", "fail");
        }
    }
}
