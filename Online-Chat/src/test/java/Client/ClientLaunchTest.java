package Client;

import org.junit.jupiter.api.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientLaunchTest {

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
    void testWriteSetting() throws ParserConfigurationException, IOException, SAXException {
        String nameFile = "settings.xml";
        ClientSetting clientSetting = new ClientSetting("127.0.0.1", 544, "History.log");
        ClientSetting actual = ClientLaunch.writeSetting(nameFile);
        assertEquals(clientSetting.getHost(), actual.getHost());
        assertEquals(clientSetting.getPort(), actual.getPort());
        assertEquals(clientSetting.getHistoryAddress(), actual.getHistoryAddress());
    }
}
