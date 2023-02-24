package Server;


import Client.ClientLaunch;
import Client.ClientSetting;
import org.junit.jupiter.api.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServerTest {
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
        ServerSettings serverSetting = new ServerSettings("127.0.0.1", 544, "ServerHistory.log");
        ServerSettings actual = Server.writeSetting(nameFile);
        assertEquals(serverSetting.getHost(), actual.getHost());
        assertEquals(serverSetting.getPort(), actual.getPort());
        assertEquals(serverSetting.getHistoryAddress(), actual.getHistoryAddress());
    }
}
