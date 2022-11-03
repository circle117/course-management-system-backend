package pers.joy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.joy.dao.CourseDao;
import pers.joy.entity.Course;
import pers.joy.service.CourseService;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseDao courseDao;

    @Autowired
    public CourseServiceImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public List<Course> searchByCode(String courseCode, int pageNum, int pageSize) {
        int begin = (pageNum-1)*pageSize;
        return courseDao.queryCourseByCourseCode(courseCode, begin, pageSize);
    }

    @Override
    public String getCourseSumByCCode(String courseCode) {
        return courseDao.queryCourseSumByCCode(courseCode);
    }

    @Override
    public List<Course> getSelectedCoursesInfo(String sNo, int pageNum, int pageSize) {
        int begin = (pageNum-1)*pageSize;
        return courseDao.querySelectedCoursesBySNo(sNo, begin, pageSize);
    }

    @Override
    public List<String> getCourseNameList(String tNo) {
        return courseDao.queryCourseNameListForTeacher(tNo);
    }

    @Override
    public List<Course> getCourseList(int pageNum, int pageSize) {
        int begin = (pageNum-1)*pageSize;
        return courseDao.queryAllCourse(begin, pageSize);
    }

    @Override
    public String getCourseSum() {
        return courseDao.queryCourseSum();
    }

    @Override
    public List<String> getAllCourseNameList() {
        return courseDao.queryCourseName();
    }

    @Override
    public int createCourse(Course course) {
        if (courseDao.existCourseCode(course.getCCode()).size()>0) {
            return -1;
        }
        return courseDao.insertCourse(course);
    }

    @Override
    public int deleteCourse(Course course) {
        return courseDao.deleteCourseByCCode(course);
    }

    @Override
    public int editCourse(Course course) {
        return courseDao.updateCourse(course);
    }

    @Override
    public List<String> addTeacher(String courseCode, List<String> teacherList) {
        List<String> failTeacherNo = new ArrayList<>();
        // fill up the empty tNo
        if (courseDao.existNoTeacherCourse(courseCode) != null) {
            int res = courseDao.updateTNoForExistItem(courseCode, teacherList.get(0));
            if (res<=0) {
                failTeacherNo.add(teacherList.get(0));
            }
            teacherList.remove(0);
            if (teacherList.size()==0) {
                return failTeacherNo;
            }
        }
        // add new items for other tNo
        Course course = courseDao.queryCourseInfoByCCode(courseCode);
        for (String teacher: teacherList) {
            if (courseDao.queryCourseByCCodeAndTNo(courseCode, teacher)==null) {
                course.setTNo(teacher);
                courseDao.insertCourse(course);
            } else {
                failTeacherNo.add(teacher);
            }
        }
        return failTeacherNo;
    }
}
