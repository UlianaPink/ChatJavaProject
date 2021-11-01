package com.db.edu.proxy.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerProxy {
    public static void main(String[] args) {
        try (final ServerSocket listener = new ServerSocket(9999);
             final Socket connection = listener.accept();
             final DataInputStream input = new DataInputStream(
                     new BufferedInputStream(connection.getInputStream()));
             final DataOutputStream out = new DataOutputStream(
                     new BufferedOutputStream(connection.getOutputStream()));
        ) {

            final int int1 = input.readInt();
            final int int2 = input.readInt();
            final int int3 = input.readInt();

            out.writeUTF(int1 + " < " + int2 + " < " +int3 + " < " + " <<<<<<<<");
            out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
