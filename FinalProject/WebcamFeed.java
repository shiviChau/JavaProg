package javafaces;
//import libraries
import com.github.sarxos.webcam.*;
import javax.swing.*;

/**
*	WebcamFeed class
*	@Shivi, Jack, Arjun, Rahul
*	@June 6, 2019
*/
public class WebcamFeed {
	//webcam object instantiation
	static Webcam webcam = Webcam.getDefault();
	
	/**
	*	Main Method
	*	@Param String[] args
	*/
	public static void main(String[] args)
	{
		//webcam object locked and disabled
		webcam.getLock().disable();
		webcam.setViewSize(WebcamResolution.VGA.getSize());
		
		//webcamPanel object instantiated
		WebcamPanel webcamPanel = new WebcamPanel(webcam);
		webcamPanel.setImageSizeDisplayed(true);
		webcamPanel.setMirrored(true);
		JFrame frame = new JFrame();
		frame.add(webcamPanel);
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	}
}
