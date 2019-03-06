package com.dallotech.arcard.controller;

import com.dallotech.arcard.model.dto.QrDataRequestDto;
import com.dallotech.arcard.service.QrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.validation.Valid;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static com.dallotech.arcard.constant.Urls.BASE_URL;

@RestController
@RequestMapping(BASE_URL)
public class QrController{

    @Autowired
    QrService qrService;

    @PostMapping("/qr")
    public ResponseEntity<?> getQrInformation(@Valid @RequestBody QrDataRequestDto qrDataRequestDto){
        try {
            return qrService.getUserInformationFromQrData(qrDataRequestDto);
        } catch (NoSuchPaddingException | InvalidKeyException | NoSuchAlgorithmException | IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException e) {
            e.printStackTrace();
            return new ResponseEntity("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }


}
