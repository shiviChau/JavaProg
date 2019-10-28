package javafaces;

//import Libraries
import java.io.OutputStream;
import java.net.Socket;

/**
*   ClientTrain Class
*   @Shivi, Jack, Arjun, Rahul
*   @June 6, 2019
*/
public class ClientTrain {

    /**
    *   Main Method
    * @Param String[] args
    */
    public static void main(String[] args) throws Exception
    {
        //For loop running 4 times
        for(int i = 1; i <= 4; i++)
        {
            //Command Thread to sleep/pause for 250 milliseconds
            Thread.sleep(250);
            //Call CLientImage main method with given String
            ClientImage.main(new String[0]);
            //Command Thread to sleep/pause for 250 milliseconds
            Thread.sleep(250);
        }
    }
}
