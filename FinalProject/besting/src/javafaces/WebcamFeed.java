package javafaces;

import com.github.sarxos.webcam.*;

import javax.swing.*;

public class WebcamFeed {

	static Webcam webcam = Webcam.getDefault();
	
	public static void main(String[] args)
	{
		
		webcam.getLock().disable();
		webcam.setViewSize(WebcamResolution.VGA.getSize());
		
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
