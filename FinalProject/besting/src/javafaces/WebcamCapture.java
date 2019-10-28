package javafaces;

import com.github.sarxos.webcam.Webcam;
import org.slf4j.Logger;
import java.io.*;
import javax.imageio.ImageIO;

public class WebcamCapture 
{
	public static void main(String[] args) throws IOException
	{
		Webcam webcam = Webcam.getDefault();
		webcam.open();
		ImageIO.write(webcam.getImage(), "png", new File("C:\\Users\\rahul\\match.jpg"));
		ImageResizer.resize("C:\\Users\\rahul\\match.jpg", "C:\\Users\\rahul\\match.jpg");
		webcam.close();

	}



}
