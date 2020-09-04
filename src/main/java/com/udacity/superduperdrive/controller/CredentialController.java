package com.udacity.superduperdrive.controller;

import com.udacity.superduperdrive.model.User;
import com.udacity.superduperdrive.model.form.CredentialForm;
import com.udacity.superduperdrive.service.CredentialService;
import com.udacity.superduperdrive.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String post(Authentication authentication,
                       @ModelAttribute("credentialForm") CredentialForm credentialForm,
                       Model model) {
        User currentUser = userService.getUser(authentication.getName());
        Integer rowsAffected;
        if (credentialForm.getCredentialId() == null) {
            rowsAffected = credentialService.insertCredential(credentialForm, currentUser.getUserId());
        } else {
            rowsAffected = credentialService.updateCredential(credentialForm, currentUser.getUserId());
        }
        boolean isSuccess = rowsAffected > 0;
        if (isSuccess) {
            credentialForm.setUrl("");
            credentialForm.setUsername("");
            credentialForm.setPassword("");
        }
        model.addAttribute("isSuccess", isSuccess);
        return "result";
    }

    @RequestMapping(value = "/delete/{credentialId}", method = RequestMethod.GET)
    public String delete(@PathVariable Integer credentialId, Model model) {
        Integer rowsAffected = credentialService.deleteCredential(credentialId);
        boolean isSuccess = rowsAffected > 0;
        model.addAttribute("isSuccess", isSuccess);
        return "result";
    }
}
