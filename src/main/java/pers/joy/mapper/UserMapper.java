package pers.joy.mapper;

import org.apache.ibatis.annotations.Param;
import pers.joy.entity.User;

import java.util.List;

public interface UserMapper {
    /**
     * Log in
     */
    User queryUserByUsernameAndPassword(@Param("type") String type, @Param("user") User user);

    /**
     * get user list
     */
    String queryUserSum(String type);

    List<User> queryAllUser(@Param("type") String type,
                            @Param("begin") int begin, @Param("pageSize") int pageSize);

    /**
     * query users by name (fuzzy search)
     */
    String queryUserSumByName(@Param("type") String type, @Param("name") String name);

    List<User> queryUserByName(@Param("type") String type, @Param("name") String name,
                                  @Param("begin") int begin, @Param("pageSize") int pageSize);

    /**
     * query user by no
     */
    List<User> queryUserByNo(@Param("type") String type, @Param("no") String no);

    /**
     * create new user
     */
    String queryMaxNo(@Param("type") String type, @Param("prefix") String prefix);

    String queryMaxUsername(String username);

    int insertUser(@Param("type") String type, @Param("user") User user);

    /**
     * update user info
     */
    int updateUser(@Param("type") String type, @Param("user") User user);

    /**
     * delete user
     */
    int deleteUser(@Param("type") String type, @Param("no") String no);
}
