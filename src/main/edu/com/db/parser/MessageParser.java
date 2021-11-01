package main.edu.com.db.parser;

import main.edu.com.db.message.MessageType;

public class MessageParser {
    public String parse(String message) {
        if (message.startsWith(MessageType.SEND.getType())) {
            return message.substring(MessageType.SEND.getType().length());
        } else if ((message.startsWith(MessageType.HIST.getType()))) {
            return message;
        }
        throw new IllegalArgumentException("Wrong message");
    }
}
