//Import Libraries
package javafaces;
import chn.util.*;

import java.io.OutputStream;
import java.net.Socket;

/**
*   Client Driver Method
*   @Shivi, Jack, Arjun, Rahul
*   @June 6, 2019
*/
public class ClientDriver {
    /**
    * Main Method
    */
    public static void main(String[] args) throws Exception {
        //Instantiate Object ConsoleIO
        ConsoleIO keyboard = new ConsoleIO();
        //Prompt the user if they are training
        System.out.println("Are you training?");
        String train = keyboard.readLine();
        //Instantiate Socket Object
        Socket socket = new Socket("localhost", 13085);
        OutputStream outputStream = socket.getOutputStream();
        byte[] training = train.getBytes();
        outputStream.write(training);
        outputStream.flush();
        socket.close();
        //Timer for 500 milliseconds
        Thread.sleep(500);
        //if user inputs string train
        if(train.toUpperCase().equals("TRAIN"))
        {
            //prompt the user for id, name, email, and phone number
            System.out.println("What is the id?");
            String id = keyboard.readLine();
            System.out.println("What is the name?");
            String name = keyboard.readLine();
            System.out.println("What is the email?");
            String eml = keyboard.readLine();
            System.out.println("What is the phone number?");
            String phn = keyboard.readLine();
            //500 millisecond timer
            Thread.sleep(500);
            socket = new Socket("localhost", 13085);
            outputStream = socket.getOutputStream();
            byte[] iding = id.getBytes();
            outputStream.write(id.length());
            outputStream.write(iding);
            byte[] naming = name.getBytes();
            outputStream.write(name.length());
            outputStream.write(naming);
            byte[] emailing = eml.getBytes();
            outputStream.write(eml.length());
            outputStream.write(emailing);
            byte[] phning = phn.getBytes();
            outputStream.write(phn.length());
            outputStream.write(phning);
            outputStream.flush();
            socket.close();
            Thread.sleep(500);
            ClientTrain.main(new String[0]);
        }
        //default statement if user does type any form of train
        else
        {
            socket = new Socket("localhost", 13085);
            outputStream = socket.getOutputStream();
            String id = "no";
            String name = "no";
            String eml = "no";
            String phn = "no";
            byte[] iding = id.getBytes();
            outputStream.write(id.length());
            outputStream.write(iding);
            byte[] naming = name.getBytes();
            outputStream.write(name.length());
            outputStream.write(naming);
            byte[] emailing = eml.getBytes();
            outputStream.write(eml.length());
            outputStream.write(emailing);
            byte[] phning = phn.getBytes();
            outputStream.write(phn.length());
            outputStream.write(phning);
            outputStream.flush();
            socket.close();
            Thread.sleep(500);
            ClientImage.main(new String[0]);
            ServerOutput.main(new String[0]);
        }

    }
}
