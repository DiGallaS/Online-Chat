package Client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ConnectException;
import java.net.Socket;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
public class ClientTest {
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void init() {
        System.out.println("Test started");
        System.setErr(new PrintStream(errContent));

    }

    @BeforeAll
    public static void started() {
        System.out.println("Tests started");
    }

    @AfterEach
    public void finished() {
        System.out.println("Test completed");
        System.setErr(originalErr);
    }


    @AfterAll
    public static void finishedAll() {
        System.out.println("Tests completed");
    }

    @Test
    public void testStarted() {
        Client client = new Client("127.0.0.1", 554, "file" ) ;
        client.started();
        assertEquals("Ошибка соединения. Некорректные данные порта или хоста.", errContent.toString());
    }
}
