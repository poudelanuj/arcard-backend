package com.dallotech.arcard.utils;

import org.bouncycastle.util.encoders.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

public class EncryptionDecryption {

    private static String key = "D38E64FGVB7XF4D6";
    private static final String ALGORITHM ="AES/CBC/PKCS5Padding";

    private static byte[] generateRandomIv() {
        int ivSize = 16;
        byte[] iv = new byte[ivSize];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        return iv;
    }

    private static SecretKeySpec generateKey(String keySpace) {

        return new SecretKeySpec(keySpace.getBytes(), "AES");
    }

    public static String encrypt(String messageToEncrypt) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher AEScipher = Cipher.getInstance(ALGORITHM);
        byte[] randomIV = generateRandomIv();
        AEScipher.init(Cipher.ENCRYPT_MODE, generateKey(key), new IvParameterSpec(randomIV));
        byte[] cipherText = AEScipher.doFinal(messageToEncrypt.getBytes());
        byte[] cipherMessageToStore = new byte[randomIV.length + cipherText.length];
        System.arraycopy(randomIV, 0, cipherMessageToStore, 0, randomIV.length);
        System.arraycopy(cipherText, 0, cipherMessageToStore, randomIV.length, cipherText.length);
        return new String(Base64.encode(cipherMessageToStore));
    }

    public static String decrypt(String cipherText) throws NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException, InvalidKeyException {

        Cipher AEScipher = Cipher.getInstance(ALGORITHM);
        byte[] temp = Base64.decode(cipherText);
        byte[] ivPattern = Arrays.copyOfRange(temp, 0, 16);
        byte[] message = Arrays.copyOfRange(temp, 16, temp.length);

        AEScipher.init(Cipher.DECRYPT_MODE, generateKey(key), new IvParameterSpec(ivPattern));
        //System.out.println(new String(Base64.encode(iv)));
        return new String(AEScipher.doFinal(message));
    }

}
