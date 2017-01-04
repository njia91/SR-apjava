package GUI;

import Controller.EventController;

import javax.swing.*;

/**
 * Class extends JMenu. Menu will show to JMenuItems,
 * update and Exit.
 *
 * @author Michael Andersson
 * @version 4 January 2017
 */
public class Alternativ extends JMenu {

    private EventController eventManager;


    /**
     * Constructor for Alternativ
     * @param eventManager reference to an event manager.
     */
    public Alternativ(EventController eventManager){
        super("Alternativ");
        this.eventManager = eventManager;
        createUppdatera();
        createExit();


    }

    /**
     * Sets up the Exit button.
     */
    private void createExit(){
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(actionEvent -> System.exit(0));
        super.add(exit);

    }

    /**
     * Sets up update button.
     */
    private void createUppdatera(){
        JMenuItem uppdatera = new JMenuItem("Uppdatera");
        uppdatera.addActionListener(e -> eventManager.update());
        super.add(uppdatera);
    }
}
