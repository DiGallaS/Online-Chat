package Client;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class History {
    private static History history;

    private History() {
    }

    public void writeHistory(String text, String historyAddress) throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter(historyAddress, true));
        writer.write(text + "\n");
        writer.flush();
    }

    public static History getInstance() {
        if (history == null) history = new History();
        return history;
    }

}
