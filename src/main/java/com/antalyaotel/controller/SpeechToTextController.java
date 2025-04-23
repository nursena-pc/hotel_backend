package com.antalyaotel.controller;

import com.antalyaotel.service.GoogleSpeechToTextService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/stt")
public class SpeechToTextController {

    private final GoogleSpeechToTextService sttService;

    public SpeechToTextController(GoogleSpeechToTextService sttService) {
        this.sttService = sttService;
    }

    @PostMapping(value = "/convert", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, Object>> convertAudioToText(@RequestPart("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("error", "Ses dosyası boş olamaz."));
            }

            byte[] audioBytes = file.getBytes();
            String transcript = sttService.convertSpeechToText(audioBytes);

            return ResponseEntity.ok(Map.of("transcript", transcript));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Hata oluştu: " + e.getMessage()));
        }
    }
}
