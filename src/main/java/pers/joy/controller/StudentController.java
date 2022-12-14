package pers.joy.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.web.bind.annotation.*;
import pers.joy.entity.Course;
import pers.joy.entity.Grade;
import pers.joy.service.CourseService;
import pers.joy.service.GradeService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    private final CourseService courseService;
    private final GradeService gradeService;
    private final Gson gson = new Gson();

    public StudentController(CourseService courseService, GradeService gradeService) {
        this.courseService = courseService;
        this.gradeService = gradeService;
    }

    /**
     * select course
     */
    @PostMapping("/grade/{studentNo}/{jsonCourse}")
    public String selectCourse(@PathVariable("studentNo") String studentNo,
                               @PathVariable("jsonCourse") String jsonCourse) {
        List<Course> courseList = gson.fromJson(jsonCourse, new TypeToken<ArrayList<Course>>(){}.getType());
        List<Grade> gradeList = new ArrayList<>();

        for (Course course: courseList) {
            gradeList.add(new Grade(studentNo, new Course(course.getCode()), course.getTeacher().getNo()));
        }

        List<String> res = gradeService.selectCourse(gradeList);
        if (res.size() == 0) {
            return "success";
        } else {
            return gson.toJson(res);
        }
    }

    @GetMapping("/selectedCourseNum/{studentNo}")
    public String getSelectedCourseNum(@PathVariable("studentNo") String studentNo) {
        return gradeService.getSelectedCourseSum(studentNo);
    }

    @GetMapping("/selectedCourse/{studentNo}/{pageNum}/{pageSize}")
    public String getSelectedCourse(@PathVariable("studentNo") String studentNo,
                                    @PathVariable("pageNum") int pageNum,
                                    @PathVariable("pageSize") int pageSize) {
        return gson.toJson(courseService.getSelectedCoursesInfo(studentNo, pageNum, pageSize));

    }

    @DeleteMapping("/grade/{studentNo}/{jsonGrade}")
    public String dropCourse(@PathVariable("studentNo") String studentNo,
                             @PathVariable("jsonGrade") String jsonGrade) {
        List<Course> courseList = gson.fromJson(jsonGrade, new TypeToken<ArrayList<Course>>(){}.getType());
        List<Grade> gradeList = new ArrayList<>();

        for (Course course: courseList) {
            gradeList.add(new Grade(studentNo, course, course.getTeacher().getNo()));
        }

        if (gradeService.dropCourse(gradeList)>0) {
            return "success";
        } else {
            return "fail";
        }
    }

    /**
     * Check completed courses
     */
    @GetMapping("/grade/{studentNo}")
    public String getCompletedCourse(@PathVariable("studentNo") String studentNo) {
        List<Grade> gradeList = gradeService.getCompletedCourses(studentNo);
        return gson.toJson(gradeList);
    }

    @GetMapping("/getGPA/{studentNo}")
    public String getGPA(@PathVariable("studentNo") String studentNo) {
        List<Grade> gradeList = gradeService.getCompletedCourses(studentNo);
        int gradeSum = 0;
        int creditSum = 0;
        for(Grade g: gradeList) {
            gradeSum += g.getGrade()*g.getCourse().getCredit();
            creditSum += g.getCourse().getCredit();
        }
        return String.valueOf(Math.round(gradeSum*1.0*100/creditSum)/100.0);
    }
}
