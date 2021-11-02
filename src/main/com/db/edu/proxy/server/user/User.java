package main.com.db.edu.proxy.server.user;

import java.io.*;
import java.net.Socket;

public class User {
    private final Socket socket;
    private String id;

    public User(Socket socket) {
        this.socket = socket;
        id = "Somebody";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
