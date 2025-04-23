package com.antalyaotel.controller;

import com.antalyaotel.service.GoogleDialogflowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chatbot")
@RequiredArgsConstructor
public class GoogleDialogflowController {

    private final GoogleDialogflowService dialogflowService;

    @PostMapping("/message")
    public ResponseEntity<String> chat(@RequestBody String userMessage) {
        String response = dialogflowService.detectIntent(userMessage);
        return ResponseEntity.ok(response);
    }
}

