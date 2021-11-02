package test.com.db.edu.proxy.client;

import main.com.db.edu.proxy.client.Client;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static org.mockito.Mockito.*;
public class ClientTest {
    BufferedReader readerStub = mock(BufferedReader.class);

    @Test
    public void baseTest() throws IOException {
        when(readerStub.ready()).thenReturn(true);
        when(readerStub.readLine()).thenReturn("Hello World Test");

        Client clientSut = new Client();
        clientSut.run();


    }
}
