package com.udacity.superduperdrive.mapper;


import com.udacity.superduperdrive.model.File;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface FileMapper {
    @Select("SELECT * FROM FILES WHERE fileid = #{fileId}")
    File selectFile(String fileId);

    @Select("SELECT * FROM FILES WHERE filename = #{fileName}")
    File selectFileByFilename(String fileName);

    @Select("SELECT * FROM FILES WHERE userid = #{userId}")
    List<File> selectFilesFromUser(Integer userId);

    @Select("SELECT * FROM FILES")
    List<File> selectAllFiles();

    @Insert("INSERT INTO FILES (filename, contenttype, filesize, filedata, userId) " +
            "VALUES(#{fileName}, #{contentType}, #{fileSize}, #{fileData}, #{userId})")
    Integer insertFile(File File);

    @Update("UPDATE FILES SET filename = #{fileName}, contenttype = #{contentType}, " +
            "filesize = #{fileSize}, filedate = #{fileData}, userid = #{userId} WHERE fileid = #{fileId}")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    Integer updateFile(File File);

    @Delete("DELETE FROM FILES WHERE fileid = #{fileId}")
    Integer deleteFile(Integer fileId);
}
