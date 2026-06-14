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

        addBlock(Button.Builder.newInstance().text(buttonText).value(value).actionId(actionId).label(label).style(ButtonStyle.PRIMARY).build());

        return this;
    }

    public Message button(String label, String buttonText, String value, String actionId, ButtonStyle style) {

        addBlock(Button.Builder.newInstance().text(buttonText).value(value).actionId(actionId).style(style).label(label).build());

        return this;
    }

    // Text
    public Message text(String text) {
        addBlock(Section.Builder.newInstance().section(text).build());

        return this;
    }

    // Markdown
    public Message markdown(String content) {
        addBlock(Markdown.Builder.newInstance().text(content).build());

        return this;
    }

    // Channel select
    public Message channelSelect(String label, String placeholder, String actionId) {
        addBlock(ChannelSelect.Builder.newInstance().placeholder(placeholder).actionId(actionId).build());
       
        return this;
    }

    public Message channelSelect(String label, String placeholder, String actionId, String initialChannel) {
        addBlock(ChannelSelect.Builder.newInstance().placeholder(placeholder).actionId(actionId).initialChannel(initialChannel).build());
       
        return this;
    }

    // Header
    public Message header(String text, int level) {
        addBlock(Header.Builder.newInstance().text(text).level(level).build());
        
        return this;
    }

    // Divider
    public Message divider() {
        addBlock(Divider.Builder.newInstance().build());
        
        return this;
    }

    public Message section(List<SlackElement> fields) {
        addBlock(Section.Builder.newInstance().fields(fields).build());
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
