package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.models.forms.NoteForm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private final NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public void insertNote(NoteForm noteForm) {
        Note note = new Note();
        note.setNoteTitle(noteForm.getTitle());
        note.setNoteDescription(noteForm.getDescription());
        note.setUserId(noteForm.getUserId());
        noteMapper.insertNote(note);
    }

    public List<Note> getNotes() {
        return noteMapper.selectAllNotes();
    }
}