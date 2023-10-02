

import java.io.*;
import java.net.Socket;

public class ConnectInputMessage extends  Thread{
    private Socket serverConnect;
    private InputStream inputStreamServer;

    public InputStream getInputStreamServer() {
        return inputStreamServer;
    }

    public ConnectInputMessage(){
        try {
            serverConnect = new Socket("localhost", 8887);
            inputStreamServer = serverConnect.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public void run(){

            BufferedReader in = new BufferedReader(new InputStreamReader(inputStreamServer));
            String serverMessage;
            while(true){
                try {
                    serverMessage = in.readLine();
                    if (serverMessage != null) {
                        System.out.println(serverMessage + '\n');
                        break;
                    }
                } catch (IOException e){
                    throw new RuntimeException(e);
                }

            }



        try(BufferedReader inputUser = new BufferedReader(new InputStreamReader(System.in)) ;
            PrintWriter out = new PrintWriter(serverConnect.getOutputStream() , true)){

            String userMessage;
            while(true){
                System.out.println("Enter Message:");
                try {
                    userMessage = inputUser.readLine();
                    out.println(userMessage);
                }
                catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
