package pers.joy.dao;

import pers.joy.entity.User;

import java.util.List;
import java.util.Map;

public interface TeacherDao {
    String queryTNoByTNameAndCCode(String TName, String CCode);

    List<User> queryAllTeacher();

    int insertTeacher(Map<String, String> teacher);

    int deleteTeacher(String tNo);

    int updateTeacher(String tNo, Map<String, String> editTeacher);
}
