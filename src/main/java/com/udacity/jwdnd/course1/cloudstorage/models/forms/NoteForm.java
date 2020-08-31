package com.udacity.jwdnd.course1.cloudstorage.models.forms;

public class NoteForm {
    private String title;
    private String description;
    private Integer userId;


    public NoteForm() {
    }

    public NoteForm(String title, String description, Integer userId) {
        this.title = title;
        this.description = description;
        this.userId = userId;
    }

    public boolean isValid() {
        return title != null && !title.isBlank()
                && description != null && !description.isBlank();
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
