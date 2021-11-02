package main.com.db.edu.proxy.server;

import main.com.db.edu.SocketHolder;
import main.com.db.edu.message.MessageKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerProxy {

    public static void main(String[] args) {
        final Logger logger = LoggerFactory.getLogger(ServerProxy.class);
        ServerSocket serverSocket = null;
        MessageKeeper keeper = new MessageKeeper();
        ConnectionList connections = new ConnectionList();

        try {
            serverSocket = new ServerSocket(SocketHolder.getPort());
        } catch (IOException e) {
            logger.error("Can't connect");
        }

        while (true) {
            try {
                Socket socket = serverSocket.accept();
                if (socket != null) {
                    logger.info("Caught user");
                    new ForClientThread(socket, keeper, connections).start();
                    connections.addConnection(socket);
                }
                connections.clean();
            } catch (IOException e) {
                logger.error("No users");
            }
        }
    }
}
