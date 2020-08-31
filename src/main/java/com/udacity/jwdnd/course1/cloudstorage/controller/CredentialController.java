package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.models.forms.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/credential")
public class CredentialController {
    private final UserService userService;
    private final CredentialService credentialService;

    public CredentialController(UserService userService, CredentialService credentialService) {
        this.userService = userService;
        this.credentialService = credentialService;
    }

    @PostMapping
    public String post(Authentication authentication, @ModelAttribute("credentialForm") CredentialForm credentialForm) {
        User currentUser = userService.getUser(authentication.getName());
        credentialForm.setUserId(currentUser.getUserId());
        credentialService.insetCredential(credentialForm);
        credentialForm.setUrl("");
        credentialForm.setUsername("");
        credentialForm.setPassword("");

        return "redirect:/home";
    }
}
