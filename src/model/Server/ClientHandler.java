package model.Server;


import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (

                DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));
                DataInputStream dis = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()))


        ) {
            //Verbindung erfolgreich - kommunizieren
            //Empfange text

            System.out.println("[Server]: Empfange Daten");
            String request = "";
            while (!request.equals("QUIT")){
                request = dis.readUTF();
                System.out.println("[Server]: Sende Daten");
                String response = request;
                dos.writeUTF(response);
                dos.flush();


            }

            System.out.println("[Server]: Verbinding wird getrennt");
            clientSocket.close();

        } catch (IOException e) {

            e.printStackTrace();

        }
    }
}
