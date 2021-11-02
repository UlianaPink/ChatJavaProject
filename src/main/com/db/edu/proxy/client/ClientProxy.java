package main.com.db.edu.proxy.client;

import main.com.db.edu.proxy.server.ServerProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ClientProxy {
    public static void main(String[] args) {
        final Logger logger = LoggerFactory.getLogger(ServerProxy.class);
        NameGenerator nameGenerator = new NameGenerator();
        try {
            Client client = new Client(nameGenerator.getName());
            client.run();
        } catch (IOException e) {
            logger.error("Exception occurred while starting a client ", e);
        }
    }
}
