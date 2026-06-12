package com.example.slack.clients.interfaces;

import java.net.http.HttpResponse;

public interface SlackClient {
    HttpResponse<String> post(String endpoint, String payload);
}
