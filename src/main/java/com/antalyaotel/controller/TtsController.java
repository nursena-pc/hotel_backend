package com.antalyaotel.controller;

import com.antalyaotel.service.GoogleTextToSpeechService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tts")
public class TtsController {

    @Autowired
    private GoogleTextToSpeechService ttsService;

    @PostMapping
    public ResponseEntity<byte[]> getAudio(@RequestBody String text) {
        try {
            byte[] audio = ttsService.convertTextToSpeech(text);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDisposition(ContentDisposition.attachment().filename("response.mp3").build());

            return new ResponseEntity<>(audio, headers, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
