package javafaces;
//import libraries
import com.github.sarxos.webcam.Webcam;
import org.slf4j.Logger;
import java.io.*;
import javax.imageio.ImageIO;

/**
*	WebcamCapture class
*	@Shivi, Jack, Arjun, Rahul
*	@June 6, 2019
*/
public class WebcamCapture 
{
	/**
	*	Main Method
	*	@Param String[] args
	*/
	public static void main(String[] args) throws IOException
	{
		//Webcam object initialized
		Webcam webcam = Webcam.getDefault();
		//webcam object call open method
		webcam.open();
		ImageIO.write(webcam.getImage(), "png", new File("C:\\Users\\rahul\\match.jpg"));
		ImageResizer.resize("C:\\Users\\rahul\\match.jpg", "C:\\Users\\rahul\\match.jpg");
		//webcam object call close method
		webcam.close();
	}
}
