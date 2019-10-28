package javafaces;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerOutput {
    private static String out;
    public static void main(String[] args) throws IOException
    {
        ServerSocket serverSocket = new ServerSocket(13086);
        Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();
        byte[] name = new byte[10];
        inputStream.read(name);
        out = new String(name);
        System.out.println(out);
        serverSocket.close();
    }
}
