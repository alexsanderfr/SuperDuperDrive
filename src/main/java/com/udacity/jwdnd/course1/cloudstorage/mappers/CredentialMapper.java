package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CredentialMapper {
    @Select("SELECT * FROM CREDENTIALS WHERE username = #{username}")
    Credential selectCredential(String username);

    @Select("SELECT * FROM CREDENTIALS")
    List<Credential> selectAllCredentials();

    @Insert("INSERT INTO CREDENTIALS (url, username, key, password) " +
            "VALUES(#{url}, #{username}, #{key}, #{password})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int insertCredential(Credential Credential);

    @Update("UPDATE CREDENTIALS SET url = #{url}, username = #{username}, " +
            "key = #{key}, password = #{password} WHERE username = #{username}")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int updateCredential(Credential Credential);

    @Delete("DELETE * FROM CREDENTIALS WHERE username = #{username}")
    void deleteCredential(String username);
}
