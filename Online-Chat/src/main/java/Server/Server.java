package Server;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Server {

    public static LinkedList<ServerInOut> clientList = new LinkedList<>();

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        ServerSettings serverSettings = writeSetting("settings.xml");

        try (ServerSocket serverSocket = new ServerSocket(serverSettings.getPort())) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                clientList.add(new ServerInOut(clientSocket, serverSettings.getHistoryAddress()));

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static ServerSettings writeSetting(String s) throws ParserConfigurationException, IOException, SAXException {
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
                case "historyServerAddress" -> historyAddress = node2.getTextContent();
            }
        }
        return new ServerSettings(host, port, historyAddress);
    }
}
