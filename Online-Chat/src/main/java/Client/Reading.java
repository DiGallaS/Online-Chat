package Client;

import java.io.*;
import java.net.Socket;

public class Reading extends Thread {
    private final Socket socket;
    private final String historyAddress;
    private final History history = History.getInstance();
    private BufferedReader in;

    public Reading(Socket socket, String historyAddress) {
        this.socket = socket;
        this.historyAddress = historyAddress;
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String text;
        try {
            while (true) {
                text = in.readLine();
                history.writeHistory(text, historyAddress);
                System.out.println(text);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
