package javafaces;
//import libraries
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
/**
*   ImageResizer class
*   @Shivi, Jack, Arjun, Rahul
*   @June 6, 2019
*/
public class ImageResizer {
    /**
    *   resize void method
    *   @Param String inputImagePath, outputImagePath
    */
    public static void resize(String inputImagePath, String outputImagePath)
            throws IOException {
        // reads input image
        File inputFile = new File(inputImagePath);
        BufferedImage inputImage = ImageIO.read(inputFile);
 
        // creates output image
        BufferedImage outputImage = new BufferedImage(176,
                150, inputImage.getType());
 
        // scales the input image to the output image
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, 176, 150, null);
        g2d.dispose();
 
        // extracts extension of output file
        String formatName = outputImagePath.substring(outputImagePath
                .lastIndexOf(".") + 1);
 
        // writes to output file
        ImageIO.write(outputImage, formatName, new File(outputImagePath));
        File inputFile2 = new File(inputImagePath);
        BufferedImage inputImage2 = ImageIO.read(inputFile2);
        BufferedImage croppedImage = inputImage2.getSubimage(25, 0, 125, 150);
        ImageIO.write(croppedImage, formatName, new File(outputImagePath));
        
        
    }
    
}
