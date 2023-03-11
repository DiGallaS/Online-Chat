package Server;


import java.io.*;
import java.net.Socket;


public class ServerInOut extends Thread {
    private final Socket socket;
    private String name;
    private final String historyAddress;
    private final ServerHistory history = ServerHistory.getInstance();
    private boolean firstMSG = true;


    public ServerInOut(Socket socket, String historyAddress) throws IOException {
        this.socket = socket;
        this.historyAddress = historyAddress;
        start();
    }

    @Override
    public void run() {
        BufferedReader in;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            name = in.readLine();
            System.out.println(name + " вошел(a) в чат.");
            send("Системное сообщение: " + name + " вошел(a) в чат.");
        } catch (IOException e) {
            System.err.println("Попытка соединения.");
            return;
        }
        String text;
        try {
            while (true) {
                text = in.readLine();
                System.out.println(text);
                if (text.equals("/exit")) {
                    history.writeHistory(name + " вышел(а) из чата.", historyAddress);
                    System.out.println(name + " вышел(а) из чата.");
                    send(name + " вышел(а) из чата.");
                    break;
                } else {
                    history.writeHistory(text, historyAddress);
                    send(text);
                }
            }
        } catch (IOException ex) {
            System.err.println("Обрыв соедиения у " + name);
            try {
                history.writeHistory("Обрыв соедиения у " + name, historyAddress);
                send(name + " вышел(а) из чата.");
                Server.clientList.remove(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void send(String text) throws IOException {
        for (ServerInOut client : Server.clientList) {
            PrintWriter out = new PrintWriter(client.socket.getOutputStream());
            if (firstMSG && client.socket == this.socket) {
                firstMSG = false;
                out.write("Системное сообщение: Добро пожаловать " + name + " !!!\n");
                out.flush();
            } else if (client.socket != this.socket) {
                out.write(text + "\n");
                out.flush();
            }
        }
    }
}
