package com.example.slack.clients;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.example.slack.clients.interfaces.SlackClient;

public class SlackHttpClient implements SlackClient {

    private static final SlackHttpClient INSTANCE = new SlackHttpClient();

    private final HttpClient httpClient;

    private SlackHttpClient() {
        this.httpClient = HttpClient.newHttpClient();
    }

    public static SlackHttpClient getInstance() {
        return INSTANCE;
    }

    @Override
    public HttpResponse<String> post(String endpoint, String payload) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(payload))
                .build();

            return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            throw new RuntimeException("Failed to post to Slack", e);
        }
    }
}