package Client2;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Writing extends Thread {
    private final Socket socket;
    private final String name;
    private PrintWriter out;
    private Scanner scanner;
    private final String historyAddress;
    private final History history = History.getInstance();


    public Writing(Socket socket, String name, String historyAddress) {
        this.socket = socket;
        this.name = name;
        this.historyAddress = historyAddress;
    }

    @Override
    public void run() {
        try {
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            scanner = new Scanner(System.in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            String text;
            Date time = new Date();
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String dtime = format.format(time);
            text = scanner.nextLine();
            if (text.equals("/exit")) {
                out.println(text);
                this.downClient();
                break;
            } else if (!text.equals("")) {
                String msg = "(" + dtime + ") " + name + ": " + text;
                try {
                    history.writeHistory(msg, historyAddress);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                out.println(msg);
            }
            out.flush();
        }
    }

    private void downClient() {
        try {
            if (!socket.isClosed()) {
                socket.close();
                out.close();
                scanner.close();
            }
        } catch (IOException ignored) {
        }
    }
}
