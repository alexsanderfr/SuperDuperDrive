package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.model.form.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.service.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/note")
public class NoteController {
    private final UserService userService;
    private final NoteService noteService;

    public NoteController(UserService userService, NoteService noteService) {
        this.userService = userService;
        this.noteService = noteService;
    }

    @PostMapping
    public String post(Authentication authentication, @ModelAttribute("noteForm") NoteForm noteForm) {
        User currentUser = userService.getUser(authentication.getName());
        Integer rowsAffected;
        if (noteForm.getNoteId() == null) {
            rowsAffected = noteService.insertNote(noteForm, currentUser.getUserId());
        } else {
            rowsAffected = noteService.updateNote(noteForm, currentUser.getUserId());
        }
        if (rowsAffected > 0) {
            noteForm.setTitle("");
            noteForm.setDescription("");
        }
        return "redirect:/home";
    }

    @RequestMapping(value = "/delete/{noteId}", method = RequestMethod.GET)
    public String delete(@PathVariable Integer noteId) {
        Integer rowsAffected = noteService.deleteNote(noteId);
        return "redirect:/home";
    }

}
