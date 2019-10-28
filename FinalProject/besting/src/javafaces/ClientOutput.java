package javafaces;

//Import Libraries
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
*   ClientOutput class
*   @Shivi, Jack, Arjun, Rahul
*   @June 6, 2019
*/
public class ClientOutput {
    /**
    *   Main Method
    *   @Param String[] args
    */
    public static void main(String[] args) throws IOException {
        //instantiate new Socket Object
        Socket socket = new Socket("localhost", 13086);
        OutputStream outputStream = socket.getOutputStream();
        String output = args[0];
        //instantiate p Object with Person Constructor
        Person p = new Person(output);
        //Pass Person p to the method TakeAttendence, clocking them in
        JDBCExample.takeAttendence(p);
        byte[] name = output.getBytes();
        //Pass name to the Output stream
        outputStream.write(name);
        outputStream.flush();
        //close socket object
        socket.close();
    }
}
