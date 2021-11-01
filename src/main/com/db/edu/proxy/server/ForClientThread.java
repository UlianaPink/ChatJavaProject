package main.com.db.edu.proxy.server;

import main.com.db.edu.message.StringMessage;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ForClientThread extends Thread {
    private final Socket socket;
    private final ArrayList<StringMessage> messageBuffer;

    public ForClientThread(Socket clientSocket) {
        this.socket = clientSocket;
        this.messageBuffer = new ArrayList<>();
    }

    public void run() {
        DataInputStream in;
        DataOutputStream out;

        try {
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));

            while (true) {
                workWithMessage(in, out);
                out.flush();
            }

        } catch (IOException e) {
            System.out.println("Error:" + e);
        }
    }

    private void workWithMessage(DataInputStream in, DataOutputStream out) throws IOException {
        String receivedLine = in.readUTF();
        if ("/hist".equals(receivedLine)) {
            writeHistory(out);
        } else {
            StringMessage message = new StringMessage(receivedLine);
            messageBuffer.add(message);
            out.writeUTF(message.getMessage());
        }
    }

    private void writeHistory(DataOutputStream out) throws IOException {
        for (StringMessage message : messageBuffer) {
            out.writeUTF(message.getMessage());
        }
    }
}
