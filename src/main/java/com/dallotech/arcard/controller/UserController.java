package com.dallotech.arcard.controller;

import com.dallotech.arcard.payload.UploadFileResponse;
import com.dallotech.arcard.security.CurrentUser;
import com.dallotech.arcard.security.UserPrincipal;
import com.dallotech.arcard.service.FileStorageService;
import com.dallotech.arcard.service.SessionService;
import com.dallotech.arcard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static com.dallotech.arcard.constant.Urls.BASE_URL;

@RestController
@RequestMapping(BASE_URL)
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private SessionService sessionService;

    @GetMapping("/users/data")
    public ResponseEntity<?> getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        sessionService.verifyAndGetLoggedInUser();
        return userService.getUserDtoFromUserPrincipal(userPrincipal);
    }

    @PostMapping("users/data")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        sessionService.verifyAndGetLoggedInUser();
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }



}
