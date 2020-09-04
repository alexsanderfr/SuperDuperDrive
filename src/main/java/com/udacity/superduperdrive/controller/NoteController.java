package com.udacity.superduperdrive.controller;

import com.udacity.superduperdrive.model.User;
import com.udacity.superduperdrive.model.form.NoteForm;
import com.udacity.superduperdrive.service.NoteService;
import com.udacity.superduperdrive.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String post(Authentication authentication,
                       @ModelAttribute("noteForm") NoteForm noteForm,
                       Model model) {
        User currentUser = userService.getUser(authentication.getName());
        Integer rowsAffected;
        if (noteForm.getNoteId() == null) {
            rowsAffected = noteService.insertNote(noteForm, currentUser.getUserId());
        } else {
            rowsAffected = noteService.updateNote(noteForm, currentUser.getUserId());
        }
        boolean isSuccess = rowsAffected > 0;
        if (isSuccess) {
            noteForm.setTitle("");
            noteForm.setDescription("");
        }
        model.addAttribute("isSuccess", isSuccess);
        return "result";
    }

    @RequestMapping(value = "/delete/{noteId}", method = RequestMethod.GET)
    public String delete(@PathVariable Integer noteId, Model model) {
        Integer rowsAffected = noteService.deleteNote(noteId);
        boolean isSuccess = rowsAffected > 0;
        model.addAttribute("isSuccess", isSuccess);
        return "result";
    }

}
