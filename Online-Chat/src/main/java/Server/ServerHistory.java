package Server;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ServerHistory {
    private static ServerHistory history;

    private ServerHistory() {
    }

    public void writeHistory(String text, String historyAddress) throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter(historyAddress, true));
        writer.write(text + "\n");
        writer.flush();
    }

    public static ServerHistory getInstance() {
        if (history == null) history = new ServerHistory();
        return history;
    }
}
