package com.dallotech.arcard.controller;

import com.dallotech.arcard.exception.ImageFileNotFoundException;
import com.dallotech.arcard.model.db.User;
import com.dallotech.arcard.model.dto.UserDto;
import com.dallotech.arcard.model.internal.LoggedUser;
import com.dallotech.arcard.payload.UploadFileResponse;
import com.dallotech.arcard.security.CurrentUser;
import com.dallotech.arcard.security.UserPrincipal;
import com.dallotech.arcard.service.FileStorageService;
import com.dallotech.arcard.service.SessionService;
import com.dallotech.arcard.service.UserService;
import com.dallotech.arcard.utils.QRGenerator;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import org.springframework.core.io.Resource;


import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.validation.Valid;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

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

    @PostMapping("user/edit")
    public ResponseEntity<?> editProfile(@Valid @RequestBody UserDto userDto){
        LoggedUser loggedUser=sessionService.verifyAndGetLoggedInUser();
        return userService.editInformation(loggedUser,userDto);

    }

    @GetMapping("users/qr_code")
    public ResponseEntity<?> downloadFile(){
        LoggedUser user=sessionService.verifyAndGetLoggedInUser();
        try {
            if(QRGenerator.createQRImage(user.getUserId())){
                Resource resource = fileStorageService.loadFileAsResource("./QrImage/"+user.getUserId().toString()+".png");
                String contentType = "image/png";
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);

            }else {
                return new ResponseEntity<>(new ImageFileNotFoundException("Image Not Found"),HttpStatus.NOT_FOUND);
            }
        } catch (WriterException | IOException | NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException | InvalidAlgorithmParameterException e) {
            return new ResponseEntity<>(new ImageFileNotFoundException("Image Not Found"), HttpStatus.NOT_FOUND);
        }
    }


}