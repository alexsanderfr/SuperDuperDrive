package com.udacity.superduperdrive.mapper;

import com.udacity.superduperdrive.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {
    @Select("SELECT * FROM USERS WHERE username = #{username}")
    User selectUser(String username);

    @Select("SELECT * FROM USERS")
    List<User> selectAllUsers();

    @Insert("INSERT INTO USERS (username, salt, password, firstname, lastname) " +
            "VALUES(#{username}, #{salt}, #{password}, #{firstName}, #{lastName})")
    Integer insertUser(User user);

    @Update("UPDATE USERS SET username = #{username}, salt = #{salt}, password = #{password}, " +
            "firstname = #{firstName}, lastname = #{lastName} WHERE username = #{username}")
    Integer updateUser(User user);

    @Delete("DELETE FROM USERS WHERE userid = #{userId}")
    Integer deleteUser(Integer userId);

}
