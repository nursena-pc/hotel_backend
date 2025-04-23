package com.antalyaotel.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.texttospeech.v1.*;
import com.google.protobuf.ByteString;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.UUID;

@Service
public class GoogleTextToSpeechService {

    private final TextToSpeechClient ttsClient;

    public GoogleTextToSpeechService() throws IOException {
        InputStream credentialsStream = new ClassPathResource("tts.json").getInputStream();
        GoogleCredentials credentials = GoogleCredentials.fromStream(credentialsStream);
        TextToSpeechSettings settings = TextToSpeechSettings.newBuilder()
                .setCredentialsProvider(() -> credentials)
                .build();
        this.ttsClient = TextToSpeechClient.create(settings);
    }

    public byte[] convertTextToSpeech(String text) throws IOException {
        SynthesisInput input = SynthesisInput.newBuilder().setText(text).build();

        VoiceSelectionParams voice = VoiceSelectionParams.newBuilder()
                .setLanguageCode("tr-TR")
                .setSsmlGender(SsmlVoiceGender.FEMALE)
                .build();

        AudioConfig audioConfig = AudioConfig.newBuilder()
                .setAudioEncoding(AudioEncoding.MP3)
                .build();

        SynthesizeSpeechResponse response = ttsClient.synthesizeSpeech(input, voice, audioConfig);
        ByteString audioContents = response.getAudioContent();

        return audioContents.toByteArray();
    }
}
