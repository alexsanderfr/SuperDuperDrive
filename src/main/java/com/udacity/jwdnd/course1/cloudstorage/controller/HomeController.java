package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.models.forms.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.models.forms.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"", "/", "/home"})
public class HomeController {


    private final UserService userService;
    private final NoteService noteService;
    private final FileService fileService;
    private final CredentialService credentialService;

    public HomeController(UserService userService,
                          NoteService noteService,
                          FileService fileService,
                          CredentialService credentialService) {
        this.userService = userService;
        this.noteService = noteService;
        this.fileService = fileService;
        this.credentialService = credentialService;
    }

    @GetMapping
    public String get(Authentication authentication,
                      @ModelAttribute("noteForm") NoteForm noteForm,
                      @ModelAttribute("credentialForm") CredentialForm credentialForm,
                      Model model) {
        if (!authentication.isAuthenticated()) {
            return "redirect:/login";
        }
        String username = authentication.getName();
        User currentUser = userService.getUser(username);
        Integer userId = currentUser.getUserId();

        model.addAttribute("userFiles", fileService.getFilesFromUser(userId));
        model.addAttribute("userNotes", noteService.getNotesFromUser(userId));
        model.addAttribute("userCredentials", credentialService.getCredentialsFromUser(userId));
        return "home";
    }

}
