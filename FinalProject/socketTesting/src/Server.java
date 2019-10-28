// A Java program for a Server 
import java.net.*;
import java.io.*;

public class Server
{
    //initialize socket and input stream 
    private Socket          socket   = null;
    private ServerSocket    server   = null;
    private DataInputStream in       =  null;

    private String id;
    private String firstName;
    private String lastName;

    // constructor with port 
    public Server(int port)
    {
        // starts server and waits for a connection 
        try
        {
            InetAddress address = InetAddress.getLocalHost();

            server = new ServerSocket(port);
            System.out.println("Server started");

            System.out.println("Waiting for a client ...");

            socket = server.accept();
            System.out.println("Client accepted");

            // takes input from the client socket 
            in = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));

            String line = "";

            // reads message from client until "Over" is sent 
            while (!line.toLowerCase().equals("over"))
            {
                //get id
                try
                {
                    line = in.readUTF();
                    id = line;

                }
                catch(IOException i)
                {
                    System.out.println(i);
                }

                //get first name
                try
                {
                    line = in.readUTF();
                    System.out.println(line);

                }
                catch(IOException i)
                {
                    System.out.println(i);
                }
            }
            System.out.println("Closing connection");

            // close connection 
            socket.close();
            in.close();
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }

    public static void main(String args[])
    {
        /*
        Class c = Server.class;
        String className = c.getName();
        System.out.println("The FQN is: " + className);
        */
        try
        {
            System.out.println(InetAddress.getLocalHost());
        }
        catch(Exception e)
        {
            System.out.print(e);
        }

        System.out.println("\narjun\n");
        Server server = new Server(33065);

    }
}