package GUI;


import javax.swing.*;
import java.awt.*;


/**
 * Extends JPanel, shows loading screen.
 *
 * @author Michael Andersson
 * @version 4 January 2017
 */
public class LoadingScreen extends JPanel{

    /**
     * Constructor for LoadingScreen.
     */
    public LoadingScreen(Dimension d){
        super();
        this.setPreferredSize(d);

        JLabel imageLabel = new JLabel();
        ImageIcon image = new ImageIcon("images/loading.gif");

        imageLabel.setIcon(image);
        this.add(imageLabel, BorderLayout.CENTER);

    }
}
