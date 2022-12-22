package pers.joy.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.web.bind.annotation.*;
import pers.joy.entity.Course;
import pers.joy.entity.User;
import pers.joy.service.*;

import java.util.LinkedList;
import java.util.List;

@RestController
public class AdministratorController {

    private final StudentService studentService;
    private final TeacherService teacherService;
    private final GradeService gradeService;
    private final CourseService courseService;
    private final DepartmentService departmentService;
    private final Gson gson = new Gson();

    public AdministratorController(StudentService studentService, TeacherService teacherService,
                                   GradeService gradeService, CourseService courseService,
                                   DepartmentService departmentService) {
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.gradeService = gradeService;
        this.courseService = courseService;
        this.departmentService = departmentService;
    }

    /**
     * Grade Management
     */
    @GetMapping("/courseName")
    public String getCourseNameList() {
        List<String> courseNameList = courseService.getAllCourseNameList();
        return gson.toJson(courseNameList);
    }

    @GetMapping("/completedCourseStudent/{courseName}")
    public String getCompletedCourseStudent(@PathVariable("courseName") String courseName) {
        return gson.toJson(gradeService.getCompletedCourseStudent(courseName));
    }

    @GetMapping("/selectedCourseStudent/{courseName}")
    public String getSelectedCourseStudent(@PathVariable("courseName") String courseName) {
        return gson.toJson(gradeService.getSelectedCourseStudent(courseName));
    }

    @PutMapping("/grade/{studentNo}/{grade}/{courseName}")
    public String submitGrade(@PathVariable("studentNo") String studentNo,
                              @PathVariable("grade") String grade,
                              @PathVariable("courseName") String courseName) {
        int res = gradeService.submitGrade(studentNo, courseName, grade);
        if (res > 0) {
            return "success";
        } else {
            return "fail";
        }
    }

    /**
     * Course Management
     */

    @PostMapping("/course/{jsonData}")
    public String addCourse(@PathVariable("jsonData") String jsonData) {
        Course course = gson.fromJson(jsonData, Course.class);
        int res = courseService.createCourse(course);
        if (res > 0) {
            return "success";
        } else {
            return "fail";
        }
    }

    @GetMapping("/courseNum")
    public String getCourseNumber() {
        return courseService.getCourseSum();
    }

    @GetMapping("/course/{pageNum}/{pageSize}")
    public String getCourse(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) {
        List<Course> courseList = courseService.getCourseList(pageNum, pageSize);
        return gson.toJson(courseList);
    }

    @PutMapping("/addTeacher/{courseCode}/{jsonTeacher}")
    public String addTeacher(@PathVariable("courseCode") String courseCode,
                             @PathVariable("jsonTeacher") String jsonTeacher) {
        List<String> teacherList = gson.fromJson(jsonTeacher, new TypeToken<LinkedList<String>>(){}.getType());

        List<String> failTeacherNo = courseService.addTeacher(courseCode, teacherList);
        if (failTeacherNo.size() == 0) {
            return "success";
        } else {
            return gson.toJson(failTeacherNo);
        }
    }

    @PutMapping("/course/{jsonCourse}")
    public String editCourse(@PathVariable("jsonCourse") String jsonCourse) {
        Course course = gson.fromJson(jsonCourse, Course.class);

        int res = courseService.editCourse(course);
        if (res >= 0) {
            return "success";
        } else {
            return "fail";
        }
    }

    @DeleteMapping("/course/{courseCode}/{teacherNo}")
    public String deleteCourse(@PathVariable("courseCode") String courseCode,
                               @PathVariable("teacherNo") String teacherNo) {
        if ("null".equals(teacherNo)) {
            teacherNo = null;
        }
        int res = courseService.deleteCourse(courseCode, teacherNo);
        if (res > 0) {
            return "success";
        } else {
            return "fail";
        }
    }

    /**
     * Student Management
     */

    @GetMapping("/studentNum")
    public String getStudentNum() {
        return studentService.getStudentSum();
    }

    @GetMapping("/student/{pageNum}/{pageSize}")
    public String getStudent(@PathVariable("pageNum") int pageNum,
                             @PathVariable("pageSize") int pageSize) {
        List<User> userList = studentService.getStudentList(pageNum, pageSize);
        return gson.toJson(userList);
    }

    @GetMapping("/studentNum/{studentName}")
    public String getSearchedStudentNum(@PathVariable("studentName") String studentName) {
        return studentService.getStudentSumByName(studentName);
    }

    @GetMapping("/student/{studentName}/{pageNum}/{pageSize}")
    public String getSearchedStudent(@PathVariable("studentName") String studentName,
                                     @PathVariable("pageNum") int pageNum,
                                     @PathVariable("pageSize") int pageSize) {
        List<User> studentList = studentService.getStudentListByName(studentName, pageNum, pageSize);
        return gson.toJson(studentList);
    }

    @PostMapping("/student/{jsonStudent}")
    public String addStudent(@PathVariable("jsonStudent") String jsonStudent) {
        User student = gson.fromJson(jsonStudent, User.class);

        int res = studentService.createStudent(student);
        if (res <= 0) {
            return "fail";
        } else {
            return "success";
        }
    }

    @PutMapping("/student/{jsonStudent}")
    public String editStudent(@PathVariable("jsonStudent") String jsonStudent) {
        User student = gson.fromJson(jsonStudent, User.class);

        int res = studentService.editStudent(student);
        if (res > 0) {
            return "success";
        } else {
            return "fail";
        }
    }

    @DeleteMapping("/student/{studentNo}")
    public String deleteStudent(@PathVariable("studentNo") String studentNo) {
        int res = studentService.deleteStudent(studentNo);
        if (res>0) {
            return "success";
        } else {
            return "fail";
        }
    }

    @GetMapping("/department")
    public String getDepartment() {
        return gson.toJson(departmentService.getDepartmentList());
    }

    /**
     * Teacher Management
     */

    @GetMapping("/teacherNum")
    public String getTeacherNum() {
        return teacherService.getTeacherSum();
    }

    @GetMapping("/teacher/{pageNum}/{pageSize}")
    public String getTeacher(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) {
        List<User> teacherList = teacherService.getTeacherList(pageNum, pageSize);
        return gson.toJson(teacherList);
    }

    @GetMapping("/teacherNum/{teacherName}")
    public String getSearchedTeacherNum(@PathVariable("teacherName") String teacherName) {
        return teacherService.getTeacherSumByName(teacherName);
    }

    @GetMapping("/teacher/{teacherName}/{pageNum}/{pageSize}")
    public String getSearchedTeacher(@PathVariable("teacherName") String teacherName,
                                     @PathVariable("pageNum") int pageNum,
                                     @PathVariable("pageSize") int pageSize) {
        List<User> teacherList = teacherService.getTeacherListByName(teacherName, pageNum, pageSize);
        return gson.toJson(teacherList);
    }

    @PostMapping("/teacher/{jsonTeacher}")
    public String addTeacher(@PathVariable("jsonTeacher") String jsonTeacher) {
        User teacher = gson.fromJson(jsonTeacher, User.class);

        int res = teacherService.createTeacher(teacher);
        if (res > 0) {
            return "success";
        } else {
            return "fail";
        }
    }

    @PutMapping("/teacher/{jsonTeacher}")
    public String editTeacher(@PathVariable("jsonTeacher") String jsonTeacher) {
        User teacher = gson.fromJson(jsonTeacher, User.class);

        int res = teacherService.editTeacher(teacher);
        if (res > 0) {
            return "success";
        } else {
            return "fail";
        }
    }

    @DeleteMapping("/teacher/{teacherNo}")
    public String deleteTeacher(@PathVariable("teacherNo") String teacherNo) {
        int res = teacherService.deleteTeacher(teacherNo);
        if (res>0) {
            return "success";
        } else {
            return "fail";
        }
    }
}
