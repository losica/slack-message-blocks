package com.example.slack;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.ArrayList;

import com.example.slack.clients.SlackHttpClient;
import com.example.slack.clients.interfaces.SlackClient;
import com.example.slack.elements.section.elements.PlainText;
import com.example.slack.senders.SlackWebhookSender;
import com.example.slack.senders.interfaces.MessageSender;
import com.example.slack.interfaces.SlackElement;

public class App 
{
    public static void main( String[] args )
    {
        String SLACK_CHANNEL_URL = "YOU_SLACK_CHANNEL_WEBHOOK_URL";
        SlackClient slackClient = new SlackHttpClient();
        MessageSender messageSender = new SlackWebhookSender(slackClient, SLACK_CHANNEL_URL);
        Message message = new Message(messageSender);

        message.header("Slack SDK Header", 1);

        message.button("Slack Button Test", "Click me", "slack_test", "action_1");

        message.text("Hello from SDK");

        message.markdown("This is a mrkdwn section block :ghost: *this is bold*, and ~this is crossed out~, and <https://google.com|this is a link>");

        message.channelSelect("Select channel", "Select channel", "action_2");

        message.divider();
        message.header("Section 2",2);

        List<SlackElement> sectionElements = new ArrayList<SlackElement>();
        sectionElements.add(PlainText.Builder.newInstance().text("A brief history of the world...").build());
        sectionElements.add(PlainText.Builder.newInstance().text("Starts with Big bang").build());
        message.section(sectionElements);

        HttpResponse<String> success = message.send();

        System.out.println("Sent successfully? " + success);
    }
}
