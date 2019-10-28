package javafaces;

//import Libraries
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;

/**
*   ServerImage class
*   @Shivi, Jack, Arjun, Rahul
*   @June 6, 2019
*/
public class ServerImage {
    //private variable instantiations
    private static String train;
    private static String name;
    private static String id;
    private static String eml;
    private static String phn;
    /**
    *   server void method
    *   @Param train String, name String, number int
    */
    private static void server(String train, String name, int number) throws Exception
    {
        ServerSocket serverSocket = new ServerSocket(13085);
        Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();
        byte[] sizeAr = new byte[10];
        inputStream.read(sizeAr);
        int size = ByteBuffer.wrap(sizeAr).asIntBuffer().get();
        byte[] imageAr = new byte[size];
        inputStream.read(imageAr);
        BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageAr));
        File f = new File("C:\\Users\\rahul\\Downloads\\javafaces\\javafaces\\gallery\\" + name + number + ".png");

        if(train.equals("train"))
            ImageIO.write(image, "png", f);
        else
            ImageIO.write(image, "png", new File("C:\\Users\\rahul\\Downloads\\javafaces\\javafaces\\probes\\match.png"));
        serverSocket.close();
    }

    /**
    *   getTrain String method
    *   @return train
    */
    public static String getTrain() {
        return train;
    }
    
    /**
    *   String methods, getId, getName, getEml, and getPhn
    *   @return all respective variables
    */
    public static String getId() {return id;}
    public static String getName() {return name;}
    public static String getEml() {return eml;}
    public static String getPhn() {return phn;}

    /**
    *   Main Method
    *   @Param String[] args
    */
    public static void main(String[] args) throws Exception {
        //ServerSocket Object Instantiation
        ServerSocket serverSocket = new ServerSocket(13085);
        Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();
        byte[] training = new byte[5];
        inputStream.read(training);
        train = new String(training);
        //ServerSocket Object closed
        serverSocket.close();
        //ServerSocket Object Instantiation
        serverSocket = new ServerSocket(13085);
        socket = serverSocket.accept();
        inputStream = socket.getInputStream();
        int idlength = inputStream.read();
        byte[] iding = new byte[idlength];
        inputStream.read(iding);
        id = new String(iding);
        int namelength = inputStream.read();
        byte[] naming = new byte[namelength];
        inputStream.read(naming);
        name = new String(naming);
        int emllength = inputStream.read();
        byte[] emling = new byte[emllength];
        inputStream.read(emling);
        eml = new String(emling);
        int phnlength = inputStream.read();
        byte[] phning = new byte[phnlength];
        inputStream.read(phning);
        phn = new String(phning);
        //ServerSocket Object closed
        serverSocket.close();
        //if train is equal to "train"
        if(train.equals("train"))
        {
            //for loop 4 times running server method
            for(int i = 1; i <= 4; i++)
            {
                server("train", name, i);
            }
        }
        else //else train is not equal to "train" and calls server with no
        {
            server("no", "No", 0);
        }
    }
}
