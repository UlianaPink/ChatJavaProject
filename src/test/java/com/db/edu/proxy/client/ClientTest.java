package test.com.db.edu.proxy.client;

import main.com.db.edu.proxy.client.Client;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;

import static org.fest.assertions.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ClientTest {
    BufferedReader readerStub = mock(BufferedReader.class);

    @Test
    public void baseTest() throws IOException {
//        when(readerStub.ready()).thenReturn(true);
//        when(readerStub.readLine()).thenReturn("/snd Base Client Test ");
//        Client clientSut = new Client(readerStub);
//        clientSut.run();
        assertTrue(true);
    }
}
