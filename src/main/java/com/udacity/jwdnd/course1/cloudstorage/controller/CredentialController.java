package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.models.forms.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
        Integer rowsAffected;
        if (credentialForm.getCredentialId() == null) {
            rowsAffected = credentialService.insertCredential(credentialForm, currentUser.getUserId());
        } else {
            rowsAffected = credentialService.updateCredential(credentialForm, currentUser.getUserId());
        }
        if (rowsAffected > 0) {
            credentialForm.setUrl("");
            credentialForm.setUsername("");
            credentialForm.setPassword("");
        }
        return "redirect:/home";
    }

    @RequestMapping(value = "/delete/{credentialId}", method = RequestMethod.GET)
    public String delete(@PathVariable Integer credentialId) {
        Integer rowsAffected = credentialService.deleteCredential(credentialId);
        return "redirect:/home";
    }
}
