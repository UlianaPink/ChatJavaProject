package main.com.db.edu.proxy.server.user;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class User {
    private final String id;
    private final Socket socket;

    public User(Socket socket) {
        this.socket = socket;
        id = "Somebody";
    }

    public String getId() {
        return id;
    }

    public DataOutputStream connect() throws IOException {
        return new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
    }

    public boolean isClosed() {
        return socket.isClosed() || !socket.isConnected();
    }
}
