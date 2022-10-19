package pers.joy.service.impl;

import pers.joy.dao.*;
import pers.joy.dao.impl.*;
import pers.joy.dao.impl.AdministratorDao;
import pers.joy.dao.impl.StudentDaoImpl;
import pers.joy.dao.impl.TeacherDaoImpl;
import pers.joy.entity.Course;
import pers.joy.entity.Grade;
import pers.joy.entity.User;
import pers.joy.service.AdministratorService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AdministratorServiceImpl implements AdministratorService {

    private final pers.joy.dao.TeacherDao teacherDao = new TeacherDaoImpl();
    private final CourseDao courseDao = new CourseDaoImpl();
    private final pers.joy.dao.StudentDao studentDao = new StudentDaoImpl();
    private final GradeDao gradeDao = new GradeDaoImpl();
    private final pers.joy.dao.AdministratorDao administratorDao = new AdministratorDao();

    @Override
    public User signIn(User user) {
        return administratorDao.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public List<User> getTeacherList() {
        return teacherDao.queryAllTeacher();
    }

    @Override
    public List<Course> getCourseList() {
        return courseDao.queryAllCourse();
    }

    @Override
    public List<User> getStudentList() {
        return studentDao.queryAllStudent();
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

    @Override
    public int createStudent(Map<String, String> student) {
        return studentDao.insertStudent(student);
    }

    @Override
    public int editStudent(String sNo, Map<String, String> updateStudent) {
        if (updateStudent.size()==0) {
            return -1;
        } else {
            return studentDao.editStudent(sNo, updateStudent);
        }
    }

    @Override
    public int deleteStudent(String sNo) {
        return studentDao.deleteStudent(sNo);
    }

    @Override
    public int createTeacher(User teacher) {
        if (teacherDao.queryTeacherByTNo(teacher.getNo())!=null) {
            return -1;
        }
        return teacherDao.insertTeacher(teacher);
    }

    @Override
    public int deleteTeacher(String tNo) {
        return teacherDao.deleteTeacher(tNo);
    }

    @Override
    public int editTeacher(User teacher) {
        return teacherDao.updateTeacher(teacher);
    }

    @Override
    public List<Object> getCourseNameList() {
        return courseDao.queryCourseName();
    }

    @Override
    public List<Grade> getCompletedCourseStudent(String cName) {
        return gradeDao.queryForCompletedCourseStudent(cName);
    }

    @Override
    public List<User> getSelectedCourseStudent(String cName) {
        return gradeDao.queryForSelectedCourseStudent(cName);
    }

    @Override
    public int inputGrade(String sNo, String cName, String grade) {
        String cCode = courseDao.queryCCodeByName(cName);
        String point = String.valueOf(getPoint(Integer.parseInt(grade)));
        return gradeDao.updateGrade(sNo, cCode, grade, point);
    }

    private float getPoint(int grade) {
        if (grade >= 90) {
            return 4.0f;
        } else if (grade>=85) {
            return 3.7f;
        } else if (grade>=82) {
            return 3.3f;
        } else if (grade>=78) {
            return 3.0f;
        } else if (grade>=75) {
            return 2.7f;
        } else if (grade>=72) {
            return 2.3f;
        } else if (grade>=68) {
            return 2.0f;
        } else if (grade>=66) {
            return 1.7f;
        } else if (grade>=64) {
            return 1.5f;
        } else if (grade>=60) {
            return 1.0f;
        } else {
            return 0.0f;
        }
    }
}
