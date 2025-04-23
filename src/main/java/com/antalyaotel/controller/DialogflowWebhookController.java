package com.antalyaotel.controller;

import com.antalyaotel.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/dialogflow")
public class DialogflowWebhookController {

        @Autowired
        private ChatService chatService;

        @PostMapping
        public ResponseEntity<Map<String, Object>> handleDialogflow(@RequestBody Map<String, Object> requestBody) {
            Map<String, Object> queryResult = (Map<String, Object>) requestBody.get("queryResult");
            if (queryResult == null || !queryResult.containsKey("queryText")) {
                return ResponseEntity.badRequest().body(Map.of("error", "Invalid request body"));
            }

            String userText = ((String) queryResult.get("queryText")).toLowerCase();

            String responseText;
            if (userText.equals("__welcome")) {
                responseText = "Selam, ben Miranda! 😎✋ Tatil planında sana destek olmak için buradayım. Hayalindeki oteli bulmana ve tüm sorularına cevap vermek için sabırsızlanıyorum! 🤩";
            } else {
                responseText = chatService.getResponse(userText);
            }

            Map<String, Object> fulfillmentMessage = new HashMap<>();
            fulfillmentMessage.put("text", Map.of("text", new String[]{responseText}));

            Map<String, Object> response = new HashMap<>();
            response.put("fulfillmentMessages", new Object[]{fulfillmentMessage});

            return ResponseEntity.ok(response);
        }
    }

