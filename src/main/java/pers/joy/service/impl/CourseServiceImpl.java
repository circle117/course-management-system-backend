package pers.joy.service.impl;

import org.springframework.stereotype.Service;
import pers.joy.entity.Course;
import pers.joy.mapper.CourseMapper;
import pers.joy.service.CourseService;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseMapper courseMapper;

    public CourseServiceImpl(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }

    @Override
    public List<Course> searchByCode(String courseCode, int pageNum, int pageSize) {
        int begin = (pageNum-1)*pageSize;
        return courseMapper.queryCourseByCourseCode("%"+courseCode+"%", begin, pageSize);
    }

    @Override
    public String getCourseSumByCCode(String courseCode) {
        return courseMapper.queryCourseSumByCourseCode("%"+courseCode+"%");
    }

    @Override
    public List<Course> getSelectedCoursesInfo(String sNo, int pageNum, int pageSize) {
        int begin = (pageNum-1)*pageSize;
        return courseMapper.querySelectedCoursesBySNo(sNo, begin, pageSize);
    }

    @Override
    public List<String> getCourseNameList(String tNo) {
        return courseMapper.queryCourseNameListForTeacher(tNo);
    }

    @Override
    public List<Course> getCourseList(int pageNum, int pageSize) {
        int begin = (pageNum-1)*pageSize;
        return courseMapper.queryAllCourses(begin, pageSize);
    }

    @Override
    public String getCourseSum() {
        return courseMapper.queryCourseSum();
    }

    @Override
    public List<String> getAllCourseNameList() {
        return courseMapper.queryCourseName();
    }

    @Override
    public int createCourse(Course course) {
        if (courseMapper.existCourseCode(course.getCCode()).size()>0) {
            return -1;
        }
        return courseMapper.insertCourse(course);
    }

    @Override
    public int deleteCourse(String courseCode, String teacherNo) {
        return courseMapper.deleteCourseByCCodeAndTNo(courseCode, teacherNo);
    }

    @Override
    public int editCourse(Course course) {
        return courseMapper.updateCourse(course);
    }

    @Override
    public List<String> addTeacher(String courseCode, List<String> teacherList) {
        List<String> failTeacherNo = new ArrayList<>();
        // fill up the empty tNo
        if (courseMapper.existNoTeacherCourse(courseCode).size() == 1) {
            int res = courseMapper.updateTNoForExistItem(courseCode, teacherList.get(0));
            if (res<=0) {
                failTeacherNo.add(teacherList.get(0));
            }
            teacherList.remove(0);
            if (teacherList.size()==0) {
                return failTeacherNo;
            }
        }
        // add new items for other tNo
        Course course = courseMapper.queryCourseInfoByCCode(courseCode);
        for (String teacher: teacherList) {
            if (courseMapper.queryCourseByCCodeAndTNo(courseCode, teacher).size() == 0) {
                course.setTNo(teacher);
                courseMapper.insertCourse(course);
            } else {
                failTeacherNo.add(teacher);
            }
        }
        return failTeacherNo;
    }
}
