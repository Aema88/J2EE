public class MainClient {
    public static void main(String[] args) {
        ConnectInputMessage connectWithServer = new ConnectInputMessage();
        Thread tConnectInputMessage = new Thread(connectWithServer);
        tConnectInputMessage.start();
        ReceiveMessageFromServer receiveMessageFromServer = new ReceiveMessageFromServer(connectWithServer.getInputStreamServer());
        Thread tReceiveMessageFromServer = new Thread(receiveMessageFromServer);
        tReceiveMessageFromServer.start();
    }
}
