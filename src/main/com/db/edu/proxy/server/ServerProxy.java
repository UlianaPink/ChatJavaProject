package main.com.db.edu.proxy.server;

import main.com.db.edu.message.StringMessage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;
import java.util.ArrayList;

import static java.lang.System.lineSeparator;

public class ServerProxy {

    private static final ArrayList<StringMessage> messageBuffer = new ArrayList<>();

    public static void main(String[] args) {
        try (final ServerSocket listener = new ServerSocket(9999);
             final Socket connection = listener.accept();

             final DataInputStream in = new DataInputStream(
                     new BufferedInputStream(connection.getInputStream()));

             final DataOutputStream out = new DataOutputStream(
                     new BufferedOutputStream(connection.getOutputStream()));
        ) {

            mainLoop(in, out);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void mainLoop(DataInputStream in, DataOutputStream out) throws IOException {
        String sep = lineSeparator();
        while (true) {
            final String receivedMessage = in.readUTF();

            if ("/hist".equals(receivedMessage)) {
                printHistory(out);
            } else {
                StringMessage stringMessage = new StringMessage(receivedMessage);
                messageBuffer.add(stringMessage);
                printTime(stringMessage, out);
                out.writeUTF(receivedMessage + sep);
            }

            out.flush();
        }
    }

    private static void printHistory(DataOutputStream out) throws IOException {
        String sep = lineSeparator();

        for (StringMessage message : messageBuffer) {
            printTime(message, out);
            out.writeUTF(message.getMessage() + sep);
        }
    }

    private static void printTime(StringMessage message, DataOutputStream out) throws IOException {
        LocalTime time = message.getTime();
        out.writeUTF("Time "
                + time.getHour() + ":"
                + time.getMinute() + ":"
                + time.getSecond() + " Message: ");
    }
}