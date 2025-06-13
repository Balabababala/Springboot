package com.example.demo.study.encryption;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.SecureRandom;
import java.util.Random;

public class CreateKey {

    public static void main(String[] args) throws IOException {
        // 產生 32 個隨機的可見 ASCII 字元 (UTF-8 Safe)
        String allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
//      Random random = new Random();
        StringBuilder keyBuilder = new StringBuilder();

        for (int i = 0; i < 32; i++) {
            int index = random.nextInt(allowedChars.length());
            keyBuilder.append(allowedChars.charAt(index));
        }

        String key = keyBuilder.toString();
        Files.writeString(Path.of("aes_key.key"), key);

        System.out.println("AES 256-bit key saved to aes_key.key");
        System.out.println("Key: " + key);
    }
}
