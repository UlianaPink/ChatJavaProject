package main.com.db.edu.proxy.server.user;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import static java.lang.System.lineSeparator;

public class UserList {
    private final ArrayList<User> users;

    public UserList() {
        this.users = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public void sendToEveryone(String message) {
        for (User user : users) {
            try {
                DataOutputStream out = user.connectOut();
                out.writeUTF(lineSeparator() + message);
                out.flush();

            } catch (IOException e) {
                System.out.println("Error:" + e);
            }
        }
    }

    public void clean() {
        users.removeIf(User::isClosed);
    }
}