package main.com.db.edu.proxy.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NameGenerator {
    public String getName() throws IOException {
        System.out.print("Please, input your name: \n");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return reader.readLine();
    }
}
