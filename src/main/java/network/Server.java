package network;

import codes.ReadWriteFile;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    private WriteThreadServer writeThreadServer = new WriteThreadServer();

    Server() {
        try {
            serverSocket = new ServerSocket(12346);
            System.out.println("Server Ready...");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                serve(clientSocket);
            }
        } catch (Exception e) {
            System.out.println("Server starts: " + e);
        }
    }

    public void serve(Socket clientSocket) throws IOException {
        NetworkUtil networkUtil = new NetworkUtil(clientSocket);
        new ReadThreadServer(networkUtil, writeThreadServer);
    }

    public static void main(String args[]) {
        ReadWriteFile.getInstance();
        Server server = new Server();
    }
}
