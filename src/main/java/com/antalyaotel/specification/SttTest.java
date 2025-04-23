package com.antalyaotel.specification;

import com.antalyaotel.service.GoogleSpeechToTextService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SttTest {
    public static void main(String[] args) throws Exception {
        GoogleSpeechToTextService service = new GoogleSpeechToTextService();

        // Dosyayı oku
        Path path = Paths.get("src/main/resources/test.wav");
        byte[] audioBytes = Files.readAllBytes(path);

        String text = service.convertSpeechToText(audioBytes);
        System.out.println("Çözümleme: " + text);
    }
}

