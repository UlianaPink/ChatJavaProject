package com.db.edu.proxy;

import com.db.edu.parser.MessageParser;
import org.junit.jupiter.api.Test;

import static org.fest.assertions.Assertions.assertThat;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    MessageParser parser =  new MessageParser();
    String clientMessage = "";
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream stream = new PrintStream(outContent);

    @Test
    public void baseMessageSent() {
        clientMessage = "/snd hello";
        clientMessage = parser.parse(clientMessage);
        assertEquals(" hello", clientMessage);
    }

/*     @Test
   public void messageSizeOverMaxLength() {
        clientMessage =
                "/snd 12345_12345_12345_12345_12345_" +
                        "12345_12345_12345_12345_12345_" +
                        "12345_12345_12345_12345_12345_" +
                        "12345_12345_12345_12345_12345_" +
                        "12345_12345_12345_12345_12345_!";
        clientMessage = parser.parse(clientMessage);

        assertEquals(" 12345_12345_12345_12345_12345_" +
                "12345_12345_12345_12345_12345_" +
                "12345_12345_12345_12345_12345_" +
                "12345_12345_12345_12345_12345_" +
                "12345_12345_12345_12345_12345...", clientMessage);
    }

    @Test
    public void promiscuousMessageSent() {
        clientMessage = "/snd Hello Мир!№;(%?:%№Ё";
        clientMessage = parser.parse(clientMessage);

        assertEquals(" Hello Мир!№;(%?:%№Ё", clientMessage);
    } */


 /*   @Test
    public void noCommandBeforeMessageSent() {
        clientMessage = "Hello";
        final IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,()->parser.parse(clientMessage));
        assertThat(thrown).hasMessage("Wrong message");
    } */




}
