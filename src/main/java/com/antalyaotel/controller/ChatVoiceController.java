package com.antalyaotel.controller;

import com.antalyaotel.service.ChatService;
import com.antalyaotel.service.GoogleTextToSpeechService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.Map;

@RestController
@RequestMapping("/api/chat-voice")
public class ChatVoiceController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private GoogleTextToSpeechService ttsService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> chatWithVoice(@RequestBody Map<String, String> request) {
        try {
            // 1. Kullanıcının mesajını al
            String message = request.get("message");

            // 2. Özel yanıt sisteminden cevap al
            String responseText = chatService.getResponse(message);

            // 3. Cevabı sese dönüştür
            byte[] audio = ttsService.convertTextToSpeech(responseText);

            // 4. Sesi Base64'e çevir
            String audioBase64 = Base64.getEncoder().encodeToString(audio);

            // 5. JSON response olarak dön
            return ResponseEntity.ok(Map.of(
                    "response", responseText,
                    "audio", audioBase64
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "error", "Bir hata oluştu: " + e.getMessage()
            ));
        }
    }
}
