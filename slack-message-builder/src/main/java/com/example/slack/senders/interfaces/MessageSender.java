package com.example.slack.senders.interfaces;

import java.net.http.HttpResponse;

public interface MessageSender {
    HttpResponse<String> send(String payload);
}
