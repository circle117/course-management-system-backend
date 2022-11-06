package pers.joy.web;

import com.google.gson.reflect.TypeToken;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import pers.joy.entity.Grade;
import pers.joy.service.CourseService;
import pers.joy.service.GradeService;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StudentServlet extends BaseServlet{

    private CourseService courseService;
    private GradeService gradeService;

    @Override
    public void init() throws ServletException {
        super.init();
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        assert context != null;
        courseService = context.getBean(CourseService.class);
        gradeService = context.getBean(GradeService.class);
    }

    /**
     * select courses
     * must sign in
     * one student can only choose one course(course code) once.
     */
    protected void selectCourse(HttpServletRequest request, HttpServletResponse resp, Map<String, String> map) {
        String sNo = request.getParameter("sNo");
        String jsonData = request.getParameter("course");
        List<Grade> gradeList;
        gradeList = gson.fromJson(jsonData, new TypeToken<ArrayList<Grade>>(){}.getType());

        for (Grade grade: gradeList) {
            grade.setSNo(sNo);
        }

        if (sNo==null) {
            map.put("Status", "NotSignIn");
        } else {
            List<String> res = gradeService.selectCourse(gradeList);
            if (res.size() == 0) {
                map.put("Status", "Success");
            } else {
                map.put("Status", "AlreadySelected");
                map.put("Courses", gson.toJson(res));
            }
        }
    }

    /**
     * get completed courses and GPA by student No
     */
    protected void completedCourse(HttpServletRequest request, HttpServletResponse resp, Map<String, String> map) {
        String sNo = request.getParameter("sNo");
        List<Grade> gradeList = gradeService.getCompletedCourses(sNo);
        map.put("completedCourses", gson.toJson(gradeList));
        float gpa = getGPA(gradeList);
        map.put("GPA", String.valueOf(Math.round(gpa*100)/100.0));
    }

    /**
     * get selected courses by student No
     */
    protected void selectedCourse(HttpServletRequest request, HttpServletResponse resp, Map<String, String> map) {
        String sNo = request.getParameter("sNo");
        int pageNum = Integer.parseInt(request.getParameter("pageNum"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        map.put("selectedCourses", gson.toJson(courseService.getSelectedCoursesInfo(sNo, pageNum, pageSize)));
        if (pageNum==1) {
            map.put("pageCount", gradeService.getSelectedCourseSum(sNo));
        }
    }

    /**
     * drop course by student No, course code and teacher No
     */
    protected void dropCourse(HttpServletRequest request, HttpServletResponse resp, Map<String, String> map) {
        String sNo = request.getParameter("sNo");
        String jsonData = request.getParameter("dropCourse");
        List<Grade> gradeList;
        gradeList = gson.fromJson(jsonData, new TypeToken<ArrayList<Grade>>(){}.getType());
        for (Grade grade: gradeList) {
            grade.setSNo(sNo);
        }

        gradeService.dropCourse(gradeList);
        map.put("Status", "Success");
    }

    protected float getGPA(List<Grade> gradeList) {
        int gradeSum = 0;
        int creditSum = 0;
        for(Grade c: gradeList) {
            gradeSum += c.getGrade()*c.getCredit();
            creditSum += c.getCredit();
        }
        return (float) gradeSum/creditSum;
    }
}
