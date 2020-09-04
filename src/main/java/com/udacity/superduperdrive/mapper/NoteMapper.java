package com.udacity.superduperdrive.mapper;

import com.udacity.superduperdrive.model.Note;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface NoteMapper {
    @Select("SELECT * FROM NOTES WHERE notetitle = #{noteTitle}")
    Note selectNote(String noteTitle);

    @Select("SELECT * FROM NOTES WHERE userid = #{userId}")
    List<Note> selectNotesFromUser(Integer userId);

    @Select("SELECT * FROM NOTES")
    List<Note> selectAllNotes();

    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) " +
            "VALUES(#{noteTitle}, #{noteDescription}, #{userId})")
    Integer insertNote(Note Note);

    @Update("UPDATE NOTES SET notetitle = #{noteTitle}, notedescription = #{noteDescription}, " +
            "userid = #{userId} WHERE noteid = #{noteId}")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    Integer updateNote(Note Note);

    @Delete("DELETE FROM NOTES WHERE noteid = #{noteId}")
    Integer deleteNote(Integer noteId);
}
