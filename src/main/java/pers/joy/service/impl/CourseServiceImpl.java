package pers.joy.service.impl;

import org.springframework.stereotype.Service;
import pers.joy.entity.Course;
import pers.joy.entity.User;
import pers.joy.mapper.CourseMapper;
import pers.joy.service.CourseService;
import pers.joy.utils.DatabaseUtils;

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
        return courseMapper.queryCourseByCode("%"+courseCode+"%", begin, pageSize);
    }

    @Override
    public String getCourseSumByCCode(String courseCode) {
        return courseMapper.queryCourseSumByCode("%"+courseCode+"%");
    }

    @Override
    public List<Course> getSelectedCoursesInfo(String sNo, int pageNum, int pageSize) {
        int begin = (pageNum-1)*pageSize;
        return courseMapper.querySelectedCoursesByStudentNo(sNo, begin, pageSize);
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
        String currentMaxCode = courseMapper.queryMaxCode(course.getDepartment().getNo());
        if (currentMaxCode==null) {
            currentMaxCode = course.getDepartment().getNo()+"0000";
        }
        String nextCode = DatabaseUtils.generateNo(currentMaxCode);
        course.setCode(nextCode);
        return courseMapper.insertCourse(course);
    }

    @Override
    public int deleteCourse(String courseCode, String teacherNo) {
        int count = Integer.parseInt(courseMapper.queryCourseSumByCode(courseCode));
        if (count==1) {
            return courseMapper.updateCourseTeacher(courseCode);
        } else {
            return courseMapper.deleteCourseByCodeAndTeacherNo(courseCode, teacherNo);
        }
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
            int res = courseMapper.updateTeacherNoForExistItem(courseCode, teacherList.get(0));
            if (res<=0) {
                failTeacherNo.add(teacherList.get(0));
            }
            teacherList.remove(0);
            if (teacherList.size()==0) {
                return failTeacherNo;
            }
        }
        // add new items for other teacher No
        Course course = courseMapper.queryCourseInfoByCode(courseCode);
        for (String teacher: teacherList) {
            if (courseMapper.queryCourseByCodeAndTeacherNo(courseCode, teacher).size() == 0) {
                course.setTeacher(new User(teacher, null));
                courseMapper.insertCourse(course);
            } else {
                failTeacherNo.add(teacher);
            }
        }
        return failTeacherNo;
    }
}
