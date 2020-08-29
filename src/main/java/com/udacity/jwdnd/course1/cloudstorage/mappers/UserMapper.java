package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.User;
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
    int insertUser(User user);

    @Update("UPDATE USERS SET username = #{username}, salt = #{salt}, password = #{password}, " +
            "firstname = #{firstName}, lastname = #{lastName} WHERE username = #{username}")
    int updateUser(User user);

    @Delete("DELETE * FROM USERS WHERE username = #{username}")
    void deleteUser(String username);

}
