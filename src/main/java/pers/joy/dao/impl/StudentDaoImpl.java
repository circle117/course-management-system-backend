package pers.joy.dao.impl;

import pers.joy.dao.StudentDao;
import pers.joy.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StudentDaoImpl extends UserDaoImpl implements StudentDao {
    @Override
    public List<User> queryAllStudent() {
        String sql = "select * from student";
        return queryForList(User.class, sql);
    }

    @Override
    public int insertStudent(Map<String, String> user) {
        String sql = "insert into student(%s) values(%s)";
        List<String> items = new ArrayList<>();
        List<String> values = new ArrayList<>();
        for (Map.Entry<String, String> entry: user.entrySet()) {
            if (entry.getValue()==null) {
                continue;
            }
            items.add(entry.getKey());
            values.add("\"" +entry.getValue()+"\"");
        }

        return update(String.format(sql,
                String.join(", ", items),
                String.join(", ", values)));
    }

    @Override
    public int editStudent(String sNo, Map<String, String> updateStudent) {
        String sql = "update student set %s where no = ?";
        List<String> values = new ArrayList<>();
        for (Map.Entry<String, String> entry: updateStudent.entrySet()) {
            values.add(entry.getKey() + "=\"" + entry.getValue() + "\"");
        }
        sql = String.format(sql, String.join(", ", values));
        return update(sql, sNo);
    }

    @Override
    public int deleteStudent(String sNo) {
        String sql = "delete from student where no = ?";
        return update(sql, sNo);
    }
}
