package main.com.db.edu.parser;

import main.com.db.edu.message.MessageType;

import java.util.Objects;

public class MessageParser {
    public String parse(String message) {
        final int maxLength = 150;
        if (message.startsWith(MessageType.SEND.getType())) {
            return message.length() < maxLength + MessageType.SEND.getType().length()
                    ? message.substring(MessageType.SEND.getType().length())
                    : message.substring(MessageType.SEND.getType().length(), maxLength + MessageType.SEND.getType().length())
                    + "...";
        } else if (Objects.equals(MessageType.HIST.getType(), message)
                || message.startsWith(MessageType.CHROOM.getType())) {
            return message;
        } else if (message.startsWith(MessageType.CHID.getType())) {
            return message;
        } else {
            throw new IllegalArgumentException("Wrong message");
        }
    }
}
