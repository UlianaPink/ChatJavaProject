package main.com.db.edu.proxy.server;

import main.com.db.edu.message.MessageKeeper;
import main.com.db.edu.proxy.server.user.User;
import main.com.db.edu.proxy.server.user.UserList;

public class Room {
    private final String roomId;
    private final UserList users;
    private final MessageKeeper keeper;

    public Room(String roomId) {
        this.roomId = roomId;
        this.users = new UserList();
        this.keeper = new MessageKeeper(roomId);
    }

    public void addUser(User user) {
        users.addUser(user);
    }

    public void removeUser(User user) {
        users.removeUser(user);
    }
}
