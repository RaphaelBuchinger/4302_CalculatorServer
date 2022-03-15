package model.Client;


import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        System.out.println("[Client]: Verbindungsaufbau");
        try (Socket serverSocket = new Socket("localhost", 2007);

             DataInputStream dis = new DataInputStream(new BufferedInputStream(serverSocket.getInputStream()));
             DataOutputStream numb1 = new DataOutputStream(new BufferedOutputStream(serverSocket.getOutputStream()));
             DataOutputStream numb2 = new DataOutputStream(new BufferedOutputStream(serverSocket.getOutputStream()));
             DataOutputStream symbol = new DataOutputStream(new BufferedOutputStream(serverSocket.getOutputStream()));



        ) {

            System.out.println("[Client]: Verbindung mit "+serverSocket.getInetAddress()+" hergestellt");
            String input = "";
            Scanner sc = new Scanner(System.in);
            while (!input.equals("QUIT")) {

                System.out.println("[Client]: Sende die erste Zahl !");
                input = sc.nextLine();
                numb1.writeUTF(input);
                numb1.flush();

                System.out.println("[Client]: Sende die zweite Zahl !");
                input = sc.nextLine();
                numb2.writeUTF(input);
                numb2.flush();


                System.out.println("[Client]: Sende die Rechenoperation !");
                input = sc.nextLine();
                symbol.writeUTF(input);
                symbol.flush();


                System.out.println("[Client]: Empfange Text!");
                String answer = dis.readUTF();
                System.out.println("[Client]: " + answer);
            }

        } catch (IOException e) {
            System.out.println("[Client]: Verbindung wurde getrennt");


        }finally {
            System.out.println("[Client]: Verbindung wurde getrennt");
        }
    }
}

