import java.net.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        ServerSocket server = null;
        Socket client;

        int portnumber = 8080;
        if(args.length >= 1){
            portnumber = Integer.parseInt(args[0]);
        }

        try{
            server = new ServerSocket(portnumber);
        }catch(IOException e){
            System.out.println("Could not open port: " + portnumber);
            System.exit(1);
        }
        System.out.println("Server is created " + server);

        while(true){
            try {
                System.out.println("Waiting for connection request...");
                client = server.accept();
                System.out.println("Connection established");
                String clientHost = client.getInetAddress().getHostAddress();
                int clientPort = client.getPort();
                System.out.println("Client host = " + clientHost + ", port = " + clientPort);


                InputStream clientIn = client.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(clientIn));
                String msgFromClient = br.readLine();
                System.out.println("Msg from client = " + msgFromClient);

                if(msgFromClient != null && msgFromClient.equalsIgnoreCase("bye")){
                    server.close();
                    client.close();
                    break;
                }
            }catch(Exception e){
                System.out.println("Connection failed");
            }
        }
    }
}