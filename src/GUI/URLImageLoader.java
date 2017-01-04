package GUI;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Used to Load an image from an URL.
 *
 * @author Michael Andersson
 * @version 4 January 2017
 */
public class URLImageLoader {


    /**
     * Returns an BufferedImage that has been loaded from the URL.
     * If failed to connect, loads an other image.
     * @param imageURL URL
     * @return BufferedImage
     */
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
