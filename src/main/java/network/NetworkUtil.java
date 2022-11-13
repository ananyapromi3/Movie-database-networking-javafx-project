package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

public class NetworkUtil implements Serializable {
    private Socket socket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    public NetworkUtil(String s, int port) throws IOException {
        this.socket = new Socket(s, port);
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectInputStream = new ObjectInputStream(socket.getInputStream());
    }

    public NetworkUtil(Socket s) throws IOException {
        this.socket = s;
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectInputStream = new ObjectInputStream(socket.getInputStream());
    }

    public Object read() throws IOException, ClassNotFoundException {
        return objectInputStream.readUnshared();
    }

    public void write(Object o) throws IOException {
        objectOutputStream.writeUnshared(o);
        objectOutputStream.reset();
        objectOutputStream.flush();
    }

    public void closeConnection() throws IOException {
        objectInputStream.close();
        objectOutputStream.close();
        socket.close();
    }
}

