package main.com.db.edu.message;

import java.time.LocalTime;

public class StringMessage {
    private final String value;
    private final LocalTime time;

    public StringMessage(String value) {
        this.value = value;
        time = LocalTime.now();
    }

    public String getMessage() {
        return value;
    }

    public LocalTime getTime() {
        return time;
    }
}
