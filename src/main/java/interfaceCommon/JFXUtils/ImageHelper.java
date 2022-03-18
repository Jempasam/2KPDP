package interfaceCommon.JFXUtils;

import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

/**
 *Permet différentes opérations en rapport avec les images
 * @author Samuel Demont
 *
 */
public class ImageHelper {
	public static Image createModified(Image inputImage, int seed)  {
	    int width = (int) inputImage.getWidth();
	    int height = (int) inputImage.getHeight();
	    WritableImage outputImage = new WritableImage(width,height);
	    PixelReader reader = inputImage.getPixelReader();
	    PixelWriter writer = outputImage.getPixelWriter();
	    
	    Random rand=new Random(seed);
	    float rotation=rand.nextFloat();
	    float saturation=rand.nextFloat()/2-0.25f;
	    float luminosity=rand.nextFloat()/2-0.25f;
	    boolean x_mirror=rand.nextBoolean();
	    boolean y_mirror=rand.nextBoolean();
	    
	    for (int y = 0; y < height; y++) {
	      for (int x = 0; x < width; x++) {
	        int argb = reader.getArgb(x, y);
	        int a = (argb >> 24) & 0xFF;
	        int r = (argb >> 16) & 0xFF;
	        int g = (argb >>  8) & 0xFF;
	        int b =  argb        & 0xFF;
	        
	        float[] hsb=java.awt.Color.RGBtoHSB(r, g, b, new float[3]);
	        hsb[0]+=rotation;
	        hsb[1]+=saturation;
	        hsb[2]+=luminosity;
	        argb=java.awt.Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);
	        argb=argb-(255-a<<24);
	        
	        int x2=x; if(x_mirror)x2=width-x-1;
	        int y2=y; if(y_mirror)y2=height-y-1;
	        
	        writer.setArgb(x2, y2, argb);
	      }
	    }
	    return outputImage;
	  }
}
