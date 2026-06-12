package com.example.slack;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.slack.elements.actions.elements.ChannelSelect;
import com.example.slack.elements.section.Section;
import com.example.slack.elements.section.elements.Button;
import com.example.slack.elements.section.elements.Markdown;
import com.example.slack.elements.structure.elements.Divider;
import com.example.slack.elements.structure.elements.Header;
import com.example.slack.enums.ButtonStyle;
import com.example.slack.interfaces.BlockBuilder;
import com.example.slack.interfaces.SlackElement;
import com.example.slack.senders.interfaces.MessageSender;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Message {
    private final List<BlockBuilder> builders = new ArrayList<>();

    private final MessageSender sender;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public Message(MessageSender sender) {
        this.sender = sender;
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public Message addBlock(BlockBuilder builder) {
        builders.add(builder);
        return this;
    }

    // Button
    public Message button(String label, String buttonText, String value, String actionId) {

        addBlock(new Button().text(buttonText).value(value).actionId(actionId).label(label).style(ButtonStyle.PRIMARY));

        return this;
    }

    public Message button(String label, String buttonText, String value, String actionId, ButtonStyle style) {

        addBlock(new Button().text(buttonText).value(value).actionId(actionId).style(style).label(label));

        return this;
    }

    // Text
    public Message text(String text) {
        addBlock(new Section().section(text));

        return this;
    }

    // Markdown
    public Message markdown(String content) {
        addBlock(new Markdown().markdown(content));

        return this;
    }

    // Channel select
    public Message channelSelect(String label, String placeholder, String actionId) {
        addBlock(new ChannelSelect().placeholder(placeholder).actionId(actionId));
       
        return this;
    }

    public Message channelSelect(String label, String placeholder, String actionId, String initialChannel) {
        addBlock(new ChannelSelect().placeholder(placeholder).actionId(actionId).initialChannel(initialChannel));
       
        return this;
    }

    // Header
    public Message header(String text, int level) {
        addBlock(new Header().text(text).level(level));
        
        return this;
    }

    // Divider
    public Message divider() {
        addBlock(new Divider());
        
        return this;
    }

    public Message section(List<SlackElement> fields) {
        addBlock(new Section().fields(fields));
        return this;
    }

    public HttpResponse<String> send() {
        try {
            List<Object> blocks = builders.stream()
                    .map(BlockBuilder::build)
                    .toList();
            String payload = objectMapper.writeValueAsString(Map.of("blocks", blocks));
            System.out.println(payload);
            return sender.send(payload);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize Slack message", e);
        }
    }
}
