package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by michael on 12/31/16.
 */
public class StartScreen extends JPanel{



    public StartScreen()   {
        super();
        this.setPreferredSize(new Dimension(600, 600));
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
        JLabel piclabel = new JLabel();
        piclabel.setSize(this.getPreferredSize());
        Image scaledImg = img.getScaledInstance(piclabel.getWidth(),
                piclabel.getHeight(),Image.SCALE_SMOOTH);
        piclabel.setIcon(new ImageIcon(scaledImg));
        piclabel.setHorizontalAlignment(JLabel.CENTER);
        piclabel.setVerticalAlignment(JLabel.CENTER);

        this.add(piclabel, BorderLayout.CENTER);
    }
}
