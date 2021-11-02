package main.com.db.edu.proxy.server.user;

import java.io.*;
import java.net.Socket;

public class User {
    private String id;
    private final Socket socket;

    public User(Socket socket) {
        this.socket = socket;
        id = "Somebody";
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public DataOutputStream connectOut() throws IOException {
        return new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
    }

    public DataInputStream connectIn() throws IOException {
        return new DataInputStream(new BufferedInputStream(socket.getInputStream()));
    }

    public boolean isClosed() {
        return socket.isClosed() || !socket.isConnected();
    }
}
