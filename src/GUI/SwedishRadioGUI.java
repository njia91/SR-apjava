package GUI;

import org.apache.ivy.core.event.EventManager;

import javax.swing.*;
import java.awt.*;

/**
 * @author Michael Andersson
 */
public class SwedishRadioGUI extends JFrame{


    public SwedishRadioGUI(){
        super("Sveriges Radio");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setPreferredSize(new Dimension(400, 200));
        super.pack();

    }
}
