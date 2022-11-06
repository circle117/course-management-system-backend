package pers.joy.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public abstract class BaseDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * insert, update, delete sql
     * @return -1: fail, otherwise: affected row number
     */
    public int update(String sql, Object... args){
        try {
            return jdbcTemplate.update(sql, args);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    public <T> T queryForObject(Class<T> type, String sql, Object ... args){
        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(type), args);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public <T> List<T> queryForList(Class<T> type, String sql, Object ... args){
        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(type), args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> T queryForSingleValue(Class<T> type, String sql, Object ... args){
        try {
            return jdbcTemplate.queryForObject(sql, type, args);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public <T> List<T> queryForColumnList(Class<T> type, String sql, Object ... args){
        try {
            return jdbcTemplate.queryForList(sql, type, args);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
