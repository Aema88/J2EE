import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.TreeMap;

public class ChatServer extends Thread {
    private Map<Integer, Socket> mapClient = new TreeMap<Integer,Socket>();
    @Override
    public void run(){
        try(ServerSocket server = new ServerSocket(8887)) {
            System.out.println("Server started. Waiting for clients");
            int numberClient = 1;
            Socket client = null;
            while(true){
                client = server.accept();

                Thread clientThread = new Thread(new ClientThread(client, this , numberClient));
                clientThread.setDaemon(true);
                clientThread.start();
                mapClient.put(numberClient,client);
                numberClient++;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void sendMessageForAllClients(int numberClient , String ClientMessage){
        for (Integer key : mapClient.keySet()) {
            if(key == numberClient) continue;
            Socket clientSocket = mapClient.get(key);
            try {
                new PrintWriter(clientSocket.getOutputStream(), true).println("Client #" + numberClient +
                        " " + ClientMessage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
