package test.com.db.edu.proxy;

import main.com.db.edu.parser.MessageParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    MessageParser parser = new MessageParser();
    String clientMessage = "";


    @Test
    public void baseMessageSent(){
        clientMessage = "/snd hello";
        clientMessage = parser.parse(clientMessage);
        assertEquals(" hello", clientMessage);
    }

    @Test
    public void messageSizeOverMaxLength(){
        clientMessage =
                "/snd 12345_12345_12345_12345_12345_" +
                        "12345_12345_12345_12345_12345_" +
                        "12345_12345_12345_12345_12345_" +
                        "12345_12345_12345_12345_12345_" +
                        "12345_12345_12345_12345_12345_!";
        clientMessage = parser.parse(clientMessage);

        assertEquals(">150 symbols!", clientMessage);
    }

    @Test
    public void promiscuousMessageSent(){
        clientMessage = "/snd Hello Мир!№;(%?:%№Ё";
        clientMessage = parser.parse(clientMessage);

        assertEquals(" Hello Мир!№;(%?:%№Ё", clientMessage);
    }

}
