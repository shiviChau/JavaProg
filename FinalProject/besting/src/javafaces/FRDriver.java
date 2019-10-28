package javafaces;

//import Library
import java.io.IOException;

/**
*   FRDriver class
*   @Shivi, Jack, Arjun, Rahul
*   @June 6, 2019
*/
public class FRDriver
{
	//FaceRecognition Object called
	static FaceRecognition fr = new FaceRecognition();
	
	/**
	*	Main Method
	*	@Param String[] args
	*/
	public static void main(String[] args)
	{
		String dir = "C:\\Users\\rahul\\Downloads\\javafaces\\javafaces\\gallery";
		String pic = "C:\\Users\\rahul\\Downloads\\javafaces\\javafaces\\probes\\match.png";
		String ef = "16";
		String train = null;
		//Instantiate Object ServerImage
		ServerImage si = new ServerImage();
		//trying for exception when calling the main method of ServerImage
		try {
			si.main(new String[0]);
			train = si.getTrain();
		} catch (Exception e) { //if exception is caught, printStackTrace
			e.printStackTrace();
		}
		//if the train does not equal the "train" string
		if(train.equals("train") == false)
		{
			fr.main(new String[] {dir, pic, ef});
			String out = fr.getOutputName();
			//trying for exception when calling main method of ClientOutput class
			try {
				ClientOutput.main(new String[] {out});
			} catch (IOException e) { //if exception is caught, printStackTrace
				e.printStackTrace();
			}
		}
		else //else the train does equal the "train" string
		{
			//Instantiate Person Object, passing the respective Id, Name, Email, and Phone number
			Person p = new Person((Integer.parseInt(si.getId())), si.getName(), si.getEml(), si.getPhn());
			//Call takeAttendence public method
			JDBCExample.takeAttendence(p);
		}
	}
}
