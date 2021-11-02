package main.com.db.edu.proxy.server;

import main.com.db.edu.message.StringMessage;
import main.com.db.edu.proxy.server.user.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ForClientThread extends Thread {
    private final User user;
    private final ArrayList<Room> rooms;
    private Room room;

    public ForClientThread(User user, ArrayList<Room> rooms) {
        this.user = user;
        this.rooms = rooms;
        this.room = getRoomById("MainRoom", rooms);
    }

    private Room getRoomById(String id, ArrayList<Room> rooms) {
        for (Room room : rooms) {
            if (id.equals(room.getId())) {
                return room;
            }
        }
        return room;
    }

    public void run() {
        DataInputStream in;
        DataOutputStream out;

        try {
            in = user.connectIn();
            out = user.connectOut();

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
        switch (receivedLine) {
            case "/hist":
                room.printHistory(out);
                break;
            case "/chid":
                user.setId(in.readUTF());
                break;
            case "/checkConnection":
                out.writeUTF("/checkConnection");
                break;
            case "/chroom":
                String newRoom = in.readUTF();
                room.removeUser(user);
                room = getRoomById(newRoom, rooms);
                room.addUser(user);
                break;
            default:
                StringMessage message = new StringMessage(receivedLine, user);
                room.addMessage(message);
                room.sendToEveryone(message.getMessage());
                break;
        }
    }
}
