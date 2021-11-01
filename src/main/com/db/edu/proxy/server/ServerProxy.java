package main.com.db.edu.proxy.server;

import main.com.db.edu.SocketHolder;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerProxy {

    public static void main(String[] args) {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(SocketHolder.getPort());
        } catch (IOException e) {
            System.out.println("Can't connect");
        }

        while (true) {
            try {
                Socket socket = serverSocket.accept();
                if (socket != null) {
                    System.out.println("Caught user");
                    new ForClientThread(socket).start();
                }
            } catch (IOException e) {
                System.out.println("No users");
            }
        }
    }
}
