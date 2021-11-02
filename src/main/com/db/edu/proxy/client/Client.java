package main.com.db.edu.proxy.client;


import main.com.db.edu.SocketHolder;
import main.com.db.edu.message.MessageType;
import main.com.db.edu.parser.MessageParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.util.Objects;

public class Client {

    private final Logger logger = LoggerFactory.getLogger(Client.class);
    private String name;

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
            socket.setSoTimeout(3000);

            while (true) {
                readClientMessage(reader, out, parser);
                getInfoFromServer(input);
//                checkAvailability(out, input);
            }

        } catch (IOException e) {
            System.out.print("Oops, something went wrong, please, see the logs\n");
            logger.error("Error occurred in client ", e);
        }
    }

    private void checkAvailability(DataOutputStream out, DataInputStream input) throws IOException {
        out.writeUTF(MessageType.CHECK.getType());
        out.flush();
        if ((input.available() > 0 && !Objects.equals(input.readUTF(), MessageType.CHECK.getType()))
                || input.available() < 0) {
            System.out.print("Sorry, server is not available\n");
            throw new IllegalArgumentException("Cannot connect to server");
        }
    }

    private void getInfoFromServer(DataInputStream input) throws IOException {
        if (input.available() > 0) {
            String messageFromServer = input.readUTF();
            System.out.print(messageFromServer);
        }
    }

    private void readClientMessage(BufferedReader reader, DataOutputStream out, MessageParser parser)
            throws IOException {
        String clientMessage = "";
        if (reader.ready()) {
            clientMessage = reader.readLine();
        }
        if (clientMessage != null && !clientMessage.isEmpty()) {
            try {
                clientMessage = parser.parse(clientMessage);
                clientMessage = sendMessageSeparately(out, clientMessage);
                out.writeUTF(clientMessage);
                out.flush();
            } catch (IllegalArgumentException e) {
                logger.error("Client input is incorrect: " + clientMessage);
                System.out.print("You sent a message without any command. Please try again with existing commands. Ex: /snd message\n");
            }
        }
    }

    private String sendMessageSeparately(DataOutputStream out, String clientMessage)
            throws IOException {
        if (clientMessage.startsWith(MessageType.CHID.getType())) {
            out.writeUTF(MessageType.CHID.getType());
            out.flush();
            clientMessage = clientMessage.substring(MessageType.CHID.getType().length());
            setName(clientMessage);
        } else if (clientMessage.startsWith(MessageType.CHROOM.getType())) {
            out.writeUTF(MessageType.CHROOM.getType());
            out.flush();
            clientMessage = clientMessage.substring(MessageType.CHROOM.getType().length());
            setName(clientMessage);
        }
        return clientMessage;
    }
}
