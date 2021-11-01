package main.com.db.edu.proxy.client;


import main.com.db.edu.SocketHolder;
import main.com.db.edu.parser.MessageParser;

import java.io.*;
import java.net.Socket;

public class Client {
    private String name;

    public Client(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void run() {
        try (
                final Socket socket = new Socket(SocketHolder.getAddress(), SocketHolder.getPort());
                final DataInputStream input = new DataInputStream(
                        new BufferedInputStream(socket.getInputStream()));
                final DataOutputStream out = new DataOutputStream(
                        new BufferedOutputStream(socket.getOutputStream()));
        ) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            MessageParser parser = new MessageParser();
            while (true) {

                String clientMessage = "";
                if (reader.ready()) {
                    clientMessage = reader.readLine();
                }
                if (clientMessage != null && !clientMessage.isEmpty()) {
                    clientMessage = parser.parse(clientMessage);
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
