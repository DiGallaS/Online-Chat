package Client;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class ClientLaunch {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        ClientSetting clientSetting = writeSetting("settings.xml");
        new Client(clientSetting.getHost(), clientSetting.getPort(), clientSetting.getHistoryAddress()).started();

    }

    public static ClientSetting writeSetting(String s) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(s));

        Node root = document.getDocumentElement();
        NodeList nodeList = root.getChildNodes();
        String host = null;
        int port = 0;
        String historyAddress = "";

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node2 = nodeList.item(i);
            switch (node2.getNodeName()) {
                case "host" -> host = node2.getTextContent();
                case "port" -> port = Integer.parseInt(node2.getTextContent());
                case "historyAddress" -> historyAddress = node2.getTextContent();
            }
        }
        return new ClientSetting(host, port, historyAddress);
    }
}
