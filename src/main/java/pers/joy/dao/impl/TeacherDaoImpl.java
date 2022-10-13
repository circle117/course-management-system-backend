package pers.joy.dao.impl;

import pers.joy.dao.TeacherDao;
import pers.joy.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TeacherDaoImpl extends BaseDao implements TeacherDao {

    @Override
    public String queryTNoByTNameAndCCode(String TName, String CCode) {
        String sql = "select teacher.`no` from course inner join teacher on course.tNo = teacher.no " +
                "where tName = ? and cCode = ?";
        return (String)queryForSingleValue(sql, TName, CCode);
    }

    @Override
    public List<User> queryAllTeacher() {
        String sql = "select * from teacher";
        return queryForList(User.class, sql);
    }

    @Override
    public int insertTeacher(Map<String, String> teacher) {
        String sql = "insert into teacher(%s) values(%s)";
        List<String> items = new ArrayList<>();
        List<String> values = new ArrayList<>();
        for (Map.Entry<String, String> entry: teacher.entrySet()) {
            if (entry.getValue()==null) {
                continue;
            }
            items.add(entry.getKey());
            if (entry.getKey().equals("age")) {
                values.add(entry.getValue());
            } else {
                values.add("\""+entry.getValue()+"\"");
            }
        }
        return update(String.format(sql,
                String.join(", ", items),
                String.join(", ", values)));
    }

    @Override
    public int deleteTeacher(String tNo) {
        String sql = "delete from teacher where no = ?";
        return update(sql, tNo);
    }
}
