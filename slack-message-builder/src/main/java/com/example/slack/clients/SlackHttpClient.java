package com.example.slack.clients;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.example.slack.clients.interfaces.SlackClient;

public class SlackHttpClient implements SlackClient {

    private final HttpClient httpClient;

    public SlackHttpClient() {
        this.httpClient = HttpClient.newHttpClient();
    }

    public SlackHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public HttpResponse<String> post(String endpoint, String payload) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(payload))
                .build();

            HttpResponse<String> response = httpClient
                .send(request, HttpResponse.BodyHandlers.ofString());

            return response;
        } catch (Exception e) {
            throw new RuntimeException("Failed to post to Slack", e);
        }
    }
}
