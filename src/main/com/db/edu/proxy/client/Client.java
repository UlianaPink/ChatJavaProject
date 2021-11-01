package main.com.db.edu.proxy.client;


import main.com.db.edu.SocketHolder;
import main.com.db.edu.message.MessageType;
import main.com.db.edu.parser.MessageParser;
import main.com.db.edu.proxy.server.ServerProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.net.Socket;

public class Client {

    private final Logger logger = LoggerFactory.getLogger(Client.class);
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

            out.writeUTF(MessageType.NAME.getType());
            out.flush();
            out.writeUTF(name);
            out.flush();

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
            logger.error(String.valueOf(e.getStackTrace()));
        }
    }
}
