package test.com.db.edu.proxy;

import main.com.db.edu.parser.MessageParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void baseParserTest() {
        MessageParser parser = new MessageParser();
        String clientMessage = "/snd hello";
        clientMessage = parser.parse(clientMessage);

        assertEquals(" hello", clientMessage);
    }
}
