package pers.joy.controller;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pers.joy.service.CourseService;
import pers.joy.service.GradeService;

import java.util.List;

@RestController
public class TeacherController {

    private final GradeService gradeService;
    private final CourseService courseService;
    private final Gson gson = new Gson();

    public TeacherController(GradeService gradeService, CourseService courseService) {
        this.gradeService = gradeService;
        this.courseService = courseService;
    }

    @GetMapping("/courseName/{teacherNo}")
    public String getCourseName(@PathVariable("teacherNo") String teacherNo) {
        List<String> courseNameList = courseService.getCourseNameList(teacherNo);

        return gson.toJson(courseNameList);
    }

    @GetMapping("/completedGrade/{courseName}/{teacherNo}")
    public String getCompletedCourseStudent(@PathVariable("courseName") String courseName,
                                            @PathVariable("teacherNo") String teacherNo) {
        return gson.toJson(gradeService.getCompletedCourseStudent(courseName, teacherNo));
    }

    @GetMapping("/selectedGrade/{courseName}/{teacherNo}")
    public String getSelectedCourseStudent(@PathVariable("courseName") String courseName,
                                           @PathVariable("teacherNo") String teacherNo) {
        return gson.toJson(gradeService.getSelectedCourseStudent(courseName, teacherNo));

    }
}
