package GUI;

import Controller.EventController;
import Controller.GUIEventManager;

import javax.swing.*;

/**
 * @author Michael Andersson
 */
public class Alternativ extends JMenu {

    private EventController eventManager;

    public Alternativ(GUIEventManager eventManager){
        super("Alternativ");
        this.eventManager = eventManager;
        createUppdatera();
        createExit();


    }

    private void createExit(){


        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(actionEvent -> System.exit(0));
        super.add(exit);

    }

    private void createUppdatera(){
        JMenuItem uppdatera = new JMenuItem("Uppdatera");
        uppdatera.addActionListener(e -> eventManager.update());
        super.add(uppdatera);
    }
}
