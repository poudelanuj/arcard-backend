package com.dallotech.arcard;

import com.dallotech.arcard.utils.QRGenerator;
import com.google.zxing.WriterException;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@SpringBootTest
public class QRGeneratorTest {

    @Test
    public void generateQR() throws NoSuchPaddingException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, IOException, BadPaddingException, IllegalBlockSizeException, WriterException, InvalidKeyException {
        assert(QRGenerator.createQRImage(UUID.randomUUID()));
    }

}
