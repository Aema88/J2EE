import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
    private int numberClient;
    private final Socket clientSocket;
    private final ChatServer chatServer;
    public ClientThread(Socket clientSocket , ChatServer chatServer, int numberClient ){
        this.numberClient = numberClient;
        this.clientSocket = clientSocket;
        this.chatServer = chatServer;
    }

    @Override
    public void run() {
        try(BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())) ){
            System.out.println("Client #" + numberClient + " connected");
            new PrintWriter(clientSocket.getOutputStream(), true).println("Client #" + numberClient);
            String clientMessage = null;
            while(true){
                clientMessage = in.readLine();
                if(!"exit".equals(clientMessage) && clientMessage != null){
                    System.out.println("Client #" + numberClient + ": " + clientMessage);
                    chatServer.sendMessageForAllClients(numberClient,clientMessage);
                }
                else{
                    break;
                }
            }
        }
        catch (IOException e){
            throw  new RuntimeException(e);
        }
    }
}
