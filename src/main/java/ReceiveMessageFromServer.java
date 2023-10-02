import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ReceiveMessageFromServer extends Thread{
    private InputStream inputStreamServer;
    public ReceiveMessageFromServer(InputStream inputStreamServer){
        this.inputStreamServer = inputStreamServer;
    }
    @Override
    public void run(){
        try(BufferedReader in = new BufferedReader(new InputStreamReader(inputStreamServer))){
            String serverMessage;
            while (true){
                try{
                    serverMessage = in.readLine();
                    if (serverMessage != null){
                        System.out.println(serverMessage + "\nEnter message: ");
                    }
                } catch (IOException e){
                    System.err.println("Error reading: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
