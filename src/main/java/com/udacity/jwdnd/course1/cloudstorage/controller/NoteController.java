package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.models.forms.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.models.forms.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
