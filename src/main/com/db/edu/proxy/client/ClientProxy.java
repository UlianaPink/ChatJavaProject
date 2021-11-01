package main.com.db.edu.proxy.client;

import java.io.IOException;

public class ClientProxy {
    public static void main(String[] args) {
        NameGenerator nameGenerator = new NameGenerator();
        try {
            Client client = new Client(nameGenerator.getName());
            client.run();
        } catch (IOException ignored) {
        }
    }
}
