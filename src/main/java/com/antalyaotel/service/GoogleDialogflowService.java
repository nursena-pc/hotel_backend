package com.antalyaotel.service;

import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.dialogflow.v2.*;
import com.google.protobuf.Struct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class GoogleDialogflowService {

    private final SessionsClient sessionsClient;
    private final SessionName session;

    public GoogleDialogflowService() {
        try {
            InputStream credentialsStream = new ClassPathResource("dialogflow-otel.json").getInputStream();
            GoogleCredentials credentials = GoogleCredentials.fromStream(credentialsStream);

            SessionsSettings sessionsSettings = SessionsSettings.newBuilder()
                    .setCredentialsProvider(FixedCredentialsProvider.create(credentials))
                    .build();

            this.sessionsClient = SessionsClient.create(sessionsSettings);
            this.session = SessionName.of("otel-agent-wckq", UUID.randomUUID().toString()); // ✅ Proje ID doğruysa burası tamamdır

        } catch (IOException e) {
            throw new RuntimeException("Dialogflow istemcisi başlatılırken hata oluştu", e);
        }
    }

    public String detectIntent(String message) {
        try {
            TextInput textInput = TextInput.newBuilder()
                    .setText(message)
                    .setLanguageCode("tr") // Türkçe
                    .build();

            QueryInput queryInput = QueryInput.newBuilder()
                    .setText(textInput)
                    .build();

            DetectIntentRequest request = DetectIntentRequest.newBuilder()
                    .setSession(session.toString())
                    .setQueryInput(queryInput)
                    .build();

            DetectIntentResponse response = sessionsClient.detectIntent(request);
            return response.getQueryResult().getFulfillmentText();

        } catch (Exception e) {
            e.printStackTrace();
            return "Şu anda sorunu anlayamadım. Lütfen daha sonra tekrar dene 💔";
        }
    }
}
