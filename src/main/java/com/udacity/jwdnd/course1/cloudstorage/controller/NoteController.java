package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.models.forms.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
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
        noteForm.setUserId(currentUser.getUserId());
        if (noteForm.getNoteId() == null) {
            noteService.insertNote(noteForm);
        } else {
            noteService.updateNote(noteForm);
        }
        noteForm.setTitle("");
        noteForm.setDescription("");
        return "redirect:/home";
    }

    @RequestMapping(value = "/delete/{noteId}", method = RequestMethod.GET)
    public String delete(@PathVariable Integer noteId) {
        noteService.deleteNote(noteId);
        return "redirect:/home";
    }

}
