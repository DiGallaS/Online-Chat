package Server;

import Client.History;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class ServerHistoryTest {

    @BeforeEach
    public void init() {
        System.out.println("Test started");
    }

    @BeforeAll
    public static void started() {
        System.out.println("Tests started");
    }

    @AfterEach
    public void finished() {
        System.out.println("Test completed");
    }


    @AfterAll
    public static void finishedAll() {
        System.out.println("Tests completed");
    }

    @Test
    public void testWriteHistory() throws IOException {
        ServerHistory history = ServerHistory.getInstance();
        String nameFile = "ServerHistory.log";
        String msg = "Test message";
        File file = new File(nameFile);
        long initSize = file.length();
        history.writeHistory(msg, nameFile);
        long lastSize = file.length();
        assertTrue(lastSize > initSize);
    }
}


