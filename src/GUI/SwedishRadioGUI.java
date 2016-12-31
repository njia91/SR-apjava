package GUI;

import Controller.GUIEventManager;
//import org.apache.ivy.core.event.EventManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;


/**
 * @author Michael Andersson
 */
public class SwedishRadioGUI extends JFrame{

    private JPanel panelCont;
    private StartScreen startScreen;
    private ProgramTableau programTableau;
    private CardLayout layout;

    private static String START = "1";
    private static String PROGRAM = "2";

    public SwedishRadioGUI(StartScreen startScreen,
                           ProgramTableau programTableau){
        super("Sveriges Radio");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setPreferredSize(new Dimension(600, 600));
        this.setLayout(new BorderLayout());
        this.startScreen = startScreen;
        this.programTableau = programTableau;

        this.startScreen.setPreferredSize(this.getPreferredSize());
        this.programTableau.setPreferredSize(this.getPreferredSize());
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
        panelCont.add(programTableau, PROGRAM);
        this.add(panelCont);
    }

    public void loadProgamTableau(URL imgURL){
        programTableau.setImage(imgURL);
        layout.show(panelCont, PROGRAM);
    }

}
