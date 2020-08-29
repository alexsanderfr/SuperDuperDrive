package com.udacity.jwdnd.course1.cloudstorage.mappers;


import com.udacity.jwdnd.course1.cloudstorage.models.File;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface FileMapper {
    @Select("SELECT * FROM FILES WHERE filename = #{fileName}")
    File selectFile(String fileName);

    @Select("SELECT * FROM FILES")
    List<File> selectAllFiles();

    @Insert("INSERT INTO FILES (filename, contenttype, filesize, filedata) " +
            "VALUES(#{fileName}, #{contentType}, #{fileSize}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int insertFile(File File);

    @Update("UPDATE FILES SET filename = #{fileName}, contenttype = #{contentType}, " +
            "filesize = #{fileSize}, filedate = #{fileData} WHERE fileName = #{fileName}")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int updateFile(File File);

    @Delete("DELETE * FROM FILES WHERE filename = #{fileName}")
    void deleteFile(String fileName);
}
