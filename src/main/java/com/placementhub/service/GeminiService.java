package com.placementhub.service;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import org.springframework.stereotype.Service;

@Service
public class GeminiService {

    private final Client client;

    public GeminiService() {

        String apiKey = System.getenv("GEMINI_API_KEY");

        if (apiKey == null || apiKey.isBlank()) {
            this.client = null;
            return;
        }

        this.client = new Client.Builder()
                .apiKey(apiKey)
                .build();
    }

    public String generateAnalysis(String prompt) {

        if (client == null) {
            throw new IllegalStateException(
                    "GEMINI_API_KEY environment variable is not configured"
            );
        }

        GenerateContentResponse response =
                client.models.generateContent(
                        "gemini-3.5-flash-lite",
                        prompt,
                        null
                );

        return response.text();
    }
}