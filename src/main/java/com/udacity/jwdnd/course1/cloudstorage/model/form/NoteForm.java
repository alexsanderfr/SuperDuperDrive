package com.udacity.jwdnd.course1.cloudstorage.model.form;

public class NoteForm {
    private Integer noteId;
    private String title;
    private String description;


    public NoteForm() {
    }

    public NoteForm(Integer noteId, String title, String description, Integer userId) {
        this.noteId = noteId;
        this.title = title;
        this.description = description;
    }

    public boolean isValid() {
        return title != null && !title.isBlank()
                && description != null && !description.isBlank();
    }

    public Integer getNoteId() {
        return noteId;
    }

    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
