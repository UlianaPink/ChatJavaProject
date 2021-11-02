package com.db.edu.parser;

import com.db.edu.message.MessageType;

import java.util.Objects;

public class MessageParser {
    public String parse(String message) {
        final int maxLength = 150;
        if (message.startsWith(MessageType.SEND.getType())) {
            if (message.length() > maxLength) {
                throw new IllegalArgumentException("Sorry, but your message is too long," +
                        " please, use no more than 150 symbols\n");
            } else {
                return message.substring(MessageType.SEND.getType().length() + 1);
            }
        } else if (Objects.equals(MessageType.HIST.getType(), message)
                || message.startsWith(MessageType.CHROOM.getType())) {
            return message;
        } else if (message.startsWith(MessageType.CHID.getType())) {
            return message;
        } else if (message.startsWith(MessageType.SDNP.getType())){
            return message;
        } else {
            throw new IllegalArgumentException("You sent a message without any command." +
                    " Please try again with existing commands. Ex: /snd message\n");
        }
    }
}
