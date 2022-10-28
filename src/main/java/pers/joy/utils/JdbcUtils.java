package pers.joy.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {

    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> conns = new ThreadLocal<>();

    static {
        try {
            Properties properties = new Properties();
            // read configuration file
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            // load data from the stream
            properties.load(inputStream);

            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * get connection to the database
     * @return if return null, connection fails
     */
    public static Connection getConnection(){
        Connection conn = conns.get();

        if (conn==null) {
            try {
                conn = dataSource.getConnection();
                conns.set(conn);
                conn.setAutoCommit(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return conn;
    }

    public static void commitAndClose(){
        Connection conn = conns.get();

        if (conn!=null) {
            try {
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        conns.remove();
    }

    public static void rollbackAndClose(){
        Connection conn = conns.get();

        if (conn!=null) {
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        conns.remove();
    }
}
