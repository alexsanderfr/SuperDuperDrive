package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.models.File;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/file")
public class FileController {
    private final UserService userService;
    private final FileService fileService;

    public FileController(UserService userService, FileService fileService) {
        this.userService = userService;
        this.fileService = fileService;
    }

    @GetMapping("/{fileId}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String fileId) {
        File file = fileService.getFile(fileId);
        Resource resource = fileService.getResourceFromFile(file);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFileName() + "\"").body(resource);
    }

    @PostMapping
    public String post(Authentication authentication, @RequestParam("fileUpload") MultipartFile multipartFile) {
        User currentUser = userService.getUser(authentication.getName());
        Integer rowsAffected = fileService.insertFile(multipartFile, currentUser.getUserId());
        return "redirect:/home";
    }

    @RequestMapping(value = "/delete/{fileId}", method = RequestMethod.GET)
    public String delete(@PathVariable Integer fileId) {
        Integer rowsAffected = fileService.deleteFile(fileId);
        return "redirect:/home";
    }
}
