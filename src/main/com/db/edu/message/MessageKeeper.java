package main.com.db.edu.message;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MessageKeeper {
    private final ArrayList<StringMessage> messageBuffer;

    public MessageKeeper() {
        this.messageBuffer = new ArrayList<>();
    }

    public void addMessage(StringMessage message) {
        messageBuffer.add(message);
    }

    public void printHistory(DataOutputStream stream) throws IOException {
        for (StringMessage message : messageBuffer) {
            stream.writeUTF(message.getMessage());
        }
    }
}
