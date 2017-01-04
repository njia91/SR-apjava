package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 *
 * StartScreen for the program.
 *
 * Just loads an image.
 *
 * @author Michael Andersson
 * @version 4 January 2017
 */
public class StartScreen extends JPanel{


    /**
     * Constructor for StartScreen
     * @param d Dimension
     */
    public StartScreen(Dimension d)   {
        super();
        this.setPreferredSize(d);
        Border border = BorderFactory.createEmptyBorder(2, 2,2,2);
        super.setBorder(border);
        this.setLayout(new BorderLayout());

        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("images/srLogga.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        /* Making sure that the image is in the center. */
        JLabel picLabel = new JLabel();
        picLabel.setSize(this.getPreferredSize());
        Image scaledImg = img.getScaledInstance(picLabel.getWidth(),
                picLabel.getHeight(),Image.SCALE_SMOOTH);
        picLabel.setIcon(new ImageIcon(scaledImg));
        picLabel.setHorizontalAlignment(JLabel.CENTER);
        picLabel.setVerticalAlignment(JLabel.CENTER);

        this.add(picLabel, BorderLayout.CENTER);
    }
}
