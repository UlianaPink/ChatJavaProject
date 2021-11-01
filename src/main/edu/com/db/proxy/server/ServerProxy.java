package main.edu.com.db.proxy.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerProxy {

    private final ArrayList<String> messageBuffer;

    public ServerProxy() {
        messageBuffer = new ArrayList<>();
    }

    public void main(String[] args) {
        try (final ServerSocket listener = new ServerSocket(9999);
             final Socket connection = listener.accept();

             final DataInputStream input = new DataInputStream(
                     new BufferedInputStream(connection.getInputStream()));

             final DataOutputStream out = new DataOutputStream(
                     new BufferedOutputStream(connection.getOutputStream()));
        ) {

            while (true) {
                final String receivedMessage = input.readUTF();

                if ("/hist".equals(receivedMessage)) {
                    for (String message : messageBuffer) {
                        out.writeUTF(message);
                    }
                } else {
                    messageBuffer.add(receivedMessage);
                    out.writeUTF(receivedMessage);
                }

                out.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
