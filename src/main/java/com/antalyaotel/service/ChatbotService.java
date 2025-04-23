package com.antalyaotel.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.dialogflow.v2.*;
import com.google.protobuf.Struct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class ChatbotService {

    @Value("${google.dialogflow.credentials.path}")
    private String dialogflowCredentialsPath;

    private SessionsClient sessionsClient;
    private SessionName session;

    @PostConstruct
    public void init() {
        try {
            InputStream stream = getClass().getClassLoader().getResourceAsStream(dialogflowCredentialsPath.replace("classpath:", ""));
            GoogleCredentials credentials = GoogleCredentials.fromStream(stream);
            SessionsSettings sessionsSettings = SessionsSettings.newBuilder()
                    .setCredentialsProvider(() -> credentials)
                    .build();

            sessionsClient = SessionsClient.create(sessionsSettings);

            // Bu ID’yi her kullanıcı için benzersiz yapabilirsin (şimdilik rastgele)
            String projectId = "otel-chatbot"; // JSON içindeki project_id ile aynı olmalı
            String sessionId = UUID.randomUUID().toString();
            session = SessionName.of(projectId, sessionId);

        } catch (Exception e) {
            log.error("Dialogflow oturumu başlatılamadı 😢", e);
            throw new RuntimeException("Dialogflow bağlantısı başarısız");
        }
    }

    public String getDialogflowResponse(String userMessage) {
        try {
            TextInput.Builder textInput = TextInput.newBuilder().setText(userMessage).setLanguageCode("tr");
            QueryInput queryInput = QueryInput.newBuilder().setText(textInput).build();

            DetectIntentRequest request = DetectIntentRequest.newBuilder()
                    .setSession(session.toString())
                    .setQueryInput(queryInput)
                    .build();

            DetectIntentResponse response = sessionsClient.detectIntent(request);
            QueryResult queryResult = response.getQueryResult();

            return queryResult.getFulfillmentText(); // Botun yanıtı
        } catch (Exception e) {
            log.error("Dialogflow yanıtı alınamadı", e);
            return "Üzgünüm, şu an yardımcı olamıyorum 😢";
        }
    }
}
