package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.models.forms.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.models.forms.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping
    public String show() {
        return "home";
    }

    @PostMapping
    public String createNote(Authentication authentication,
                             @ModelAttribute("noteForm") NoteForm noteForm,
                             @ModelAttribute("credentialForm") CredentialForm credentialForm,
                             Model model) {
        User currentUser = userService.getUser(authentication.getName());
        noteForm.setUserId(currentUser.getUserId());
        noteService.insertNote(noteForm);
        noteForm.setTitle("");
        noteForm.setDescription("");
        model.addAttribute("userNotes", noteService.getNotes());
        return "home";
    }
}
