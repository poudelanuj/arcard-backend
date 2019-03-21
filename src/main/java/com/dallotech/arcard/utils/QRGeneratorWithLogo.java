package com.dallotech.arcard.utils;

import com.dallotech.arcard.model.dto.SaveImageDto;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class QRGeneratorWithLogo {

    private static final String DIR = "./QrImage/";
    private static final String ext = ".png";
    private static final int WIDTH = 125;
    private static final int HEIGHT = 125;

    public static SaveImageDto generateQRWithLogo(String email) throws IOException, NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, WriterException {


        Map<EncodeHintType, ErrorCorrectionLevel> hints = new HashMap<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);

        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix bitMatrix = null;
        ByteArrayOutputStream os = new ByteArrayOutputStream();


//            cleanDirectory(DIR);
        initDirectory(DIR);
        bitMatrix = writer.encode(EncryptionDecryption.encrypt(email), BarcodeFormat.QR_CODE, WIDTH, HEIGHT, hints);
        BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix, getMatrixConfig());
        BufferedImage overly = ImageIO.read(new File("./logo/logo.png"));
        int deltaHeight = qrImage.getHeight() - overly.getHeight();
        int deltaWidth = qrImage.getWidth() - overly.getWidth();

        BufferedImage combined = new BufferedImage(qrImage.getHeight(), qrImage.getWidth(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D) combined.getGraphics();

        g.drawImage(qrImage, 0, 0, null);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        g.drawImage(overly, (int) Math.round(deltaWidth / 2), (int) Math.round(deltaHeight / 2), null);

        ImageIO.write(combined, "png", os);
        String imageFileName = generateRandoTitle(new Random(), 9);
        Files.copy(new ByteArrayInputStream(os.toByteArray()), Paths.get(DIR + imageFileName + ext), StandardCopyOption.REPLACE_EXISTING);
        SaveImageDto saveImageDto = new SaveImageDto();
        saveImageDto.setFileName(imageFileName);
        saveImageDto.setSaveStatus(true);

        return saveImageDto;

    }


    private static void initDirectory(String DIR) throws IOException {
        Files.createDirectories(Paths.get(DIR));
    }

    private static void cleanDirectory(String DIR) {
        try {
            Files.walk(Paths.get(DIR), FileVisitOption.FOLLOW_LINKS)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        } catch (IOException e) {
            // Directory does not exist, Do nothing
        }
    }


    private static String generateRandoTitle(Random random, int length) {
        return random.ints(48, 122)
                .filter(i -> (i < 57 || i > 65) && (i < 90 || i > 97))
                .mapToObj(i -> (char) i)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }


    private static MatrixToImageConfig getMatrixConfig() {
        // ARGB Colors
        // Check Colors ENUM
        return new MatrixToImageConfig(Colors.BLACK.getArgb(), Colors.WHITE.getArgb());
    }

    public enum Colors {

        BLUE(0xFF40BAD0),
        RED(0xFFE91C43),
        PURPLE(0xFF8A4F9E),
        ORANGE(0xFFF4B13D),
        WHITE(0xFFFFFFFF),
        BLACK(0xFF000000);

        private final int argb;

        Colors(final int argb) {
            this.argb = argb;
        }

        public int getArgb() {
            return argb;
        }
    }


}
