package model.Server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {


    public static void main(String[] args) {


        ExecutorService executorService = Executors.newCachedThreadPool();
        try (ServerSocket listeningSocket = new ServerSocket(2007)) {
            System.out.println("[Server]: Warte auf verbinding");

            while (true) {

                Socket clientSocket = listeningSocket.accept();
                System.out.println("[Server]: "+listeningSocket.getInetAddress());
                //Übergebe Socket an die Protokoll Klasse
                executorService.execute(new ClientHandler(clientSocket));
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}

