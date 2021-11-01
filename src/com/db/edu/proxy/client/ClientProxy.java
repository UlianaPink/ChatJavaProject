package com.db.edu.proxy.client;

import java.io.*;
import java.net.Socket;

public class ClientProxy {
    public static void main(String[] args) {
        try (
                final Socket socket = new Socket("127.0.0.1", 9999);
                final DataInputStream input = new DataInputStream(
                        new BufferedInputStream(socket.getInputStream()));
                final DataOutputStream out = new DataOutputStream(
                        new BufferedOutputStream(socket.getOutputStream()))
        ) {
            while (true) {
                final String message = input.readUTF();
                //parsing
                out.writeUTF(message);
                out.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
