package com.udacity.superduperdrive.controller;

import com.udacity.superduperdrive.model.User;
import com.udacity.superduperdrive.model.form.CredentialForm;
import com.udacity.superduperdrive.model.form.NoteForm;
import com.udacity.superduperdrive.service.*;
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
    private final EncryptionService encryptionService;

    public HomeController(UserService userService,
                          NoteService noteService,
                          FileService fileService,
                          CredentialService credentialService,
                          EncryptionService encryptionService) {
        this.userService = userService;
        this.noteService = noteService;
        this.fileService = fileService;
        this.credentialService = credentialService;
        this.encryptionService = encryptionService;
    }

    @GetMapping
    public String get(Authentication authentication,
                      @ModelAttribute("noteForm") NoteForm noteForm,
                      @ModelAttribute("credentialForm") CredentialForm credentialForm,
                      Model model) {
        if (!authentication.isAuthenticated() || userService.getUser(authentication.getName()) == null) {
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
