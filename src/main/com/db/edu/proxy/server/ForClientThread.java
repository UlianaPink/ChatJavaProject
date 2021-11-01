package main.com.db.edu.proxy.server;

import main.com.db.edu.message.MessageKeeper;
import main.com.db.edu.message.StringMessage;

import java.io.*;
import java.net.Socket;

public class ForClientThread extends Thread {
    private final Socket socket;
    private final MessageKeeper keeper;
    private final ConnectionList connections;
    private String username;

    public ForClientThread(Socket clientSocket, MessageKeeper keeper, ConnectionList connections) {
        this.socket = clientSocket;
        this.keeper = keeper;
        this.connections = connections;
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
            keeper.printHistory(out);
        } else if ("/name".equals(receivedLine)) {
            username = in.readUTF();
        } else {
            StringMessage message = new StringMessage(receivedLine, username);
            keeper.addMessage(message);
            connections.sendToEveryone(message.getMessage());
        }
    }
}
