package main.edu.com.db.parser;

public class MessageParser {
    public String parser(String message) {
        if (message.startsWith("/snd")) {
            return message.substring("/snd ".length());
        } else if ((message.startsWith("/hist"))) {
            return "/hist";
        }
        throw new IllegalArgumentException("Wrong message");
    }
}
