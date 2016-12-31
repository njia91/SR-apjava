package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import static com.sun.javafx.css.FontFace.FontFaceSrcType.URL;

/**
 * Created by m-and_000 on 2016-12-31.
 */
public class ProgramTableau extends JPanel {

    private JLabel picLabel;
    private Image scaledImg;

    public ProgramTableau(){
        super();
        this.setPreferredSize(new Dimension(600,600));
        this.setLayout(new BorderLayout());
        this.picLabel = new JLabel();

    }



    public void setImage(URL imageURL){
        System.out.println("asdasdasdasd");

        BufferedImage img= null;
        /* This is ugly, but if internet connection fails,
        *  try to load local image.*/
        try {
            img = ImageIO.read( imageURL);
        } catch (IOException | IllegalArgumentException e){
            try {
                img = ImageIO.read(new File("images/Bild_saknas.svg.png"));
            } catch (IOException e1) {
                e1.printStackTrace();
                return;
            }
        }


        picLabel.setSize(this.getPreferredSize());
        scaledImg = img.getScaledInstance(picLabel.getWidth(),
                picLabel.getHeight()/3, Image.SCALE_SMOOTH);
        picLabel.setIcon(new ImageIcon(scaledImg));
        picLabel.setHorizontalAlignment(JLabel.CENTER);
        picLabel.setVerticalAlignment(JLabel.CENTER);

        this.add(picLabel, BorderLayout.NORTH);
        picLabel.revalidate();

    }
}
