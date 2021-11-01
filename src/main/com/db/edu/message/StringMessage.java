package main.com.db.edu.message;

import java.time.LocalTime;

public class StringMessage {
    private final String value;
    private final LocalTime time;
    private final String senderName;

    public StringMessage(String value, String name) {
        this.value = value;
        time = LocalTime.now();
        this.senderName = name;
    }

    public String getMessage() {
        return (senderName + ": " + value + " (" + time.getHour() + ":" + time.getMinute() + ":" + time.getSecond() + ")");
    }
}
