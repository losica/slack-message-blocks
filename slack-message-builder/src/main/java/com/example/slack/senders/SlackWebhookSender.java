package com.example.slack.senders;

import java.net.http.HttpResponse;

import com.example.slack.clients.interfaces.SlackClient;
import com.example.slack.senders.interfaces.MessageSender;

public class SlackWebhookSender implements MessageSender {
    private final SlackClient client;
    private final String webhookUrl;

    public SlackWebhookSender(SlackClient client, String webhookUrl) {
        this.client = client;
        this.webhookUrl = webhookUrl;
    }

    @Override
    public HttpResponse<String> send(String payload) {
        return client.post(webhookUrl, payload);
    }
}
