package main.com.db.edu.message;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.System.lineSeparator;

public class MessageKeeper {
    private final int SIZE_OF_BUFFER = 15;
    private final ArrayList<StringMessage> messageBuffer;
    private final FileController fileController;

    public MessageKeeper() {
        this.messageBuffer = new ArrayList<>();
        this.fileController = new FileController();
    }

    public void addMessage(StringMessage message) {
        messageBuffer.add(message);
        removeFirstIfOverflow();
        writeMessageToFile(message.getMessage();
    }

    private void removeFirstIfOverflow() {
        if (messageBuffer.size() > SIZE_OF_BUFFER) {
            messageBuffer.remove(0);
        }
    }

    private void writeMessageToFile(String message) {
        fileController.addMessage(message);
    }

    public void printHistory(DataOutputStream stream) throws IOException {
        for (StringMessage message : messageBuffer) {
            stream.writeUTF(message.getMessage() + lineSeparator());
        }
    }
}
