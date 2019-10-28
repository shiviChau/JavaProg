package javafaces;

//import Libraries
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;

/**
*   Class ClientImage
*   @Shivi, Jack, Arjun, Rahul
*   @June 6, 2019
*/
public class ClientImage {
    /**
    *   Main Method
    *   @Param String[] args
    */
    public static void main(String[] args) throws Exception {
        WebcamCapture.main(new String[0]);
        Thread.sleep(500);
        Socket socket = new Socket("localhost", 13085);
        OutputStream outputStream = socket.getOutputStream();
        BufferedImage image = ImageIO.read(new File("C:\\Users\\rahul\\match.jpg"));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", byteArrayOutputStream);
        byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
        outputStream.write(size);
        outputStream.write(byteArrayOutputStream.toByteArray());
        outputStream.flush();
        socket.close();
    }
}
