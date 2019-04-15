package com.dallotech.arcard;

import com.dallotech.arcard.utils.QRGenerator;
import com.google.zxing.WriterException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class GenerateQr {

    public static void main(String [] args){
        for(int i=1;i<=2000;i++){
            try {
                QRGenerator.createQRImage("dallotech",String.valueOf(i));
            } catch (WriterException | IOException | NoSuchPaddingException | InvalidKeyException | NoSuchAlgorithmException | IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException e) {
                e.printStackTrace();
            }
        }
    }

}
