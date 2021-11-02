package main.com.db.edu.proxy.server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import static java.lang.System.lineSeparator;

public class ConnectionList {
    private final ArrayList<Socket> connections;

    public ConnectionList() {
        this.connections = new ArrayList<>();
    }

    public void addConnection(Socket socket) {
        connections.add(socket);
    }

    public void sendToEveryone(String message) {
        for (Socket socket : connections) {
            try {
                DataOutputStream out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
                out.writeUTF(lineSeparator() + message);
                out.flush();

            } catch (IOException e) {
                System.out.println("Error:" + e);
            }
        }
    }

    public void clean() {
        connections.removeIf(Socket::isClosed);
    }
}
