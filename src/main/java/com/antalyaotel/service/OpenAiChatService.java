package com.antalyaotel.service;

import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OpenAiChatService {

    private final OpenAiService openAiService;

    public OpenAiChatService(@Value("${openai.api.key}") String apiKey) {
        this.openAiService = new OpenAiService(apiKey);
    }

    public String ask(String prompt) {
        CompletionRequest request = CompletionRequest.builder()
                .prompt(prompt)
                .model("text-davinci-003") // İstersen gpt-3.5-turbo veya gpt-4 için farklı client lazım
                .maxTokens(150)
                .temperature(0.7)
                .build();

        CompletionResult result = openAiService.createCompletion(request);
        return result.getChoices().get(0).getText().trim();
    }
}
