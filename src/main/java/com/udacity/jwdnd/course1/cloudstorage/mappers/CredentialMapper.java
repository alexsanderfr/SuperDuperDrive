package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CredentialMapper {
    @Select("SELECT * FROM CREDENTIALS WHERE username = #{username}")
    Credential selectCredential(String username);

    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userId}")
    List<Credential> selectCredentialsFromUser(Integer userId);

    @Select("SELECT * FROM CREDENTIALS")
    List<Credential> selectAllCredentials();

    @Insert("INSERT INTO CREDENTIALS (url, username, key, password, userId) " +
            "VALUES(#{url}, #{username}, #{key}, #{password}, #{userId})")
    Integer insertCredential(Credential Credential);

    @Update("UPDATE CREDENTIALS SET url = #{url}, username = #{username}, " +
            "key = #{key}, password = #{password}, userid = #{userId} WHERE username = #{username}")
    Integer updateCredential(Credential Credential);

    @Delete("DELETE * FROM CREDENTIALS WHERE credentialid = #{credentialId}")
    Integer deleteCredential(Integer credentialId);
}
