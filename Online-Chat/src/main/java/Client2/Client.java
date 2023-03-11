package Client2;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private final String host;
    private final int port;
    private final String historyAddress;
    private String name;

    public Client(String host, int port, String historyAddress) {
        this.host = host;
        this.port = port;
        this.historyAddress = historyAddress;
    }

    public void started() {
        Socket socket;
        try {
            socket = new Socket(host, port);
            this.name = pressName();
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            out.write(name + "\n");
            out.flush();
        } catch (IOException e) {
            System.err.print("Ошибка соединения. Некорректные данные порта или хоста.");
            return;
        }

        new Reading(socket, historyAddress).start();
        new Writing(socket, name, historyAddress).start();
    }

    private String pressName() {
        System.out.print("Укажите ваш имя: ");
        Scanner scanner = new Scanner(System.in);
        name = scanner.nextLine();
        return name;
    }
}

