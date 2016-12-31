package GUI;

import Controller.GUIEventManager;
//import org.apache.ivy.core.event.EventManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;


/**
 * @author Michael Andersson
 */
public class SwedishRadioGUI extends JFrame{

    private JPanel panelCont;
    private StartScreen startScreen;

    private CardLayout layout;

    private static String START = "1";
    private static String PROGRAM = "2";

    public SwedishRadioGUI(StartScreen startScreen){
        super("Sveriges Radio");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setPreferredSize(new Dimension(600, 600));
        this.setLayout(new BorderLayout());
        this.startScreen = startScreen;
        this.startScreen.setPreferredSize(this.getPreferredSize());

        setUpCardLayout();
        layout.show(panelCont, START);
        super.pack();
        this.setLocationRelativeTo(null);
    }


    private void setUpCardLayout(){
        panelCont = new JPanel();


        layout = new CardLayout();

        panelCont.setLayout(layout);
        panelCont.add(startScreen, START);

        this.add(panelCont);
    }

}
