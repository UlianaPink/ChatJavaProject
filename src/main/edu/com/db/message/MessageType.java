package main.edu.com.db.message;

public enum MessageType {
    SEND("/snd"),
    HIST("/hist");

    private final String type;

    MessageType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
