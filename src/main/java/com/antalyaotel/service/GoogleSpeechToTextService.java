package com.antalyaotel.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.speech.v1.*;
import com.google.protobuf.ByteString;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service
public class GoogleSpeechToTextService {

    private final SpeechClient speechClient;

    public GoogleSpeechToTextService() throws Exception {
        InputStream credentialsStream = new ClassPathResource("stt.json").getInputStream();
        GoogleCredentials credentials = GoogleCredentials.fromStream(credentialsStream);
        SpeechSettings settings = SpeechSettings.newBuilder()
                .setCredentialsProvider(() -> credentials)
                .build();
        this.speechClient = SpeechClient.create(settings);
    }

    public String convertSpeechToText(byte[] audioData) {
        try {
            RecognitionConfig config = RecognitionConfig.newBuilder()
                    .setEncoding(RecognitionConfig.AudioEncoding.LINEAR16)
                    .setLanguageCode("tr-TR")
                    .build();

            RecognitionAudio audio = RecognitionAudio.newBuilder()
                    .setContent(ByteString.copyFrom(audioData))
                    .build();

            RecognizeResponse response = speechClient.recognize(config, audio);
            List<SpeechRecognitionResult> results = response.getResultsList();

            StringBuilder transcript = new StringBuilder();
            for (SpeechRecognitionResult result : results) {
                transcript.append(result.getAlternatives(0).getTranscript());
            }

            return transcript.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Sesi anlayamadım 😔";
        }
    }
}
