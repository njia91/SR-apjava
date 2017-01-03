package GUI;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @author Michael Andersson
 */
public class URLImageLoader {


    public BufferedImage openURLImage(URL imageURL){
        BufferedImage img = null;
        try {
            img = ImageIO.read( imageURL);
        } catch (IOException | IllegalArgumentException e){
            try {
                img = ImageIO.read(new File("images/Bild_saknas.svg.png"));
            } catch (IOException e1) {
                System.err.println("Could not open image");
                System.exit(1);
            }
        }
        return img;
    }
}
