package pers.joy.service.impl;

import pers.joy.dao.CourseDao;
import pers.joy.dao.impl.CourseDaoImpl;
import pers.joy.entity.Course;
import pers.joy.service.CourseService;

import java.util.List;

public class CourseServiceImpl implements CourseService {

    private CourseDao courseDao = new CourseDaoImpl();
    @Override
    public List<Course> searchByCode(String cCode) {
        return courseDao.queryCourseByCourseCode(cCode);
    }
}
