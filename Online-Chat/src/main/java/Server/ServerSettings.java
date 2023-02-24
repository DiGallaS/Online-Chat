package Server;

public class ServerSettings {
    private final String host;
    private final int port;
    private final String historyAddress;

    public ServerSettings(String host, int port, String historyAddress) {
        this.host = host;
        this.port = port;
        this.historyAddress = historyAddress;
    }


    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getHistoryAddress() {
        return historyAddress;
    }
}
