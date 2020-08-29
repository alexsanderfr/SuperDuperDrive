package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface NoteMapper {
    @Select("SELECT * FROM NOTES WHERE notetitle = #{noteTitle}")
    Note selectNote(String noteTitle);

    @Select("SELECT * FROM NOTES")
    List<Note> selectAllNotes();

    @Insert("INSERT INTO NOTES (noteTitle, notedescription) " +
            "VALUES(#{noteTitle}, #{noteDescription})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int insertNote(Note Note);

    @Update("UPDATE NOTES SET notetitle = #{noteTitle}, notedescription = #{noteDescription} " +
            "WHERE notetitle = #{noteTitle}")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int updateNote(Note Note);

    @Delete("DELETE * FROM NOTES WHERE notetitle = #{noteTitle}")
    void deleteNote(String noteTitle);
}
