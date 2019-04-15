package com.dallotech.arcard.controller;

import com.dallotech.arcard.model.dto.UserEditRequestDto;
import com.dallotech.arcard.model.internal.LoggedUser;
import com.dallotech.arcard.payload.UploadFileResponse;
import com.dallotech.arcard.security.CurrentUser;
import com.dallotech.arcard.security.UserPrincipal;
import com.dallotech.arcard.service.FileStorageService;
import com.dallotech.arcard.service.SessionService;
import com.dallotech.arcard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

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

    @GetMapping("/user/data")
    public ResponseEntity<?> getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userService.getUserDtoFromUserPrincipal(userPrincipal);
    }

    @PostMapping("user/image")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        LoggedUser user=sessionService.verifyAndGetLoggedInUser();
        System.out.println(file.getName());
        String fileName = fileStorageService.storeFile(file, user.getUser());

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @PostMapping("/user/edit")
    public ResponseEntity<?> editProfile(@Valid @RequestBody UserEditRequestDto userEditRequestDto) {
        LoggedUser loggedUser = sessionService.verifyAndGetLoggedInUser();
        return userService.editInformation(loggedUser, userEditRequestDto);

    }

    @GetMapping("/user/image")
    public ResponseEntity<?> downloadProfileFile(){
        LoggedUser loggedUser = sessionService.verifyAndGetLoggedInUser();
        Resource resource = fileStorageService.loadFileAsResource(loggedUser.getUser().getImagePath(),"profile");
        String contentType = "image/png";
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);

    }

    @GetMapping("/user/qr_code")
    public ResponseEntity<?> downloadQrFile() {
        LoggedUser loggedUser = sessionService.verifyAndGetLoggedInUser();

        Resource resource = fileStorageService.loadFileAsResource(loggedUser.getUser().getId() + ".png","qr");
        String contentType = "image/png";
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);


    }


}