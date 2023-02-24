package Client;


public class ClientSetting {
    private final String host;
    private final int port;
    private final String historyAddress;

    public ClientSetting(String host, int port, String historyAddress) {
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
