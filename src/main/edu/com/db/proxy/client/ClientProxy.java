package main.edu.com.db.proxy.client;

import main.edu.com.db.parser.MessageParser;

import java.io.*;
import java.net.Socket;

public class ClientProxy {
    public static void main(String[] args) {
        try (
                final Socket socket = new Socket("127.0.0.1", 9999);
                final DataInputStream input = new DataInputStream(
                        new BufferedInputStream(socket.getInputStream()));
                final DataOutputStream out = new DataOutputStream(
                        new BufferedOutputStream(socket.getOutputStream()));
        ) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            MessageParser parser = new MessageParser();

            while (true) {
                // local printing and sending message
                String clientMessage = reader.readLine();
                if (clientMessage != null && !clientMessage.isEmpty()) {
                    parser.parse(clientMessage);
                    out.writeUTF(clientMessage);
                    out.flush();
                }

                // server printing
                if (input.available() > 0) {
                    String messageFromServer = input.readUTF();
                    System.out.print(messageFromServer);
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
