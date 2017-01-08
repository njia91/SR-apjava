package GUI;


import Controller.EventController;
import Controller.GUIEventManager;
//import org.apache.ivy.core.event.EventManager;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Extends JMenu. The menu is dynamically loaded depending
 * on the number of categories and channels.
 *
 * @author Michael Andersson
 * @version 4 January 2017
 */
public class Kanaler extends JMenu{

    private EventController eventManager;

    /**
     * Constructor for Kanaler
     * @param eventManager reference to an eventManager.
     */
    public Kanaler(EventController eventManager){
        super("Kanaler");
        this.eventManager = eventManager;

        /* If the menu is selected update and display categories
        *  and channels. */
        this.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                updateChannels();
            }
            @Override
            public void menuDeselected(MenuEvent e) {
                Kanaler menu = (Kanaler)e.getSource();
                menu.removeAll();
            }
            @Override
            public void menuCanceled(MenuEvent e) {
                Kanaler menu = (Kanaler)e.getSource();
                menu.removeAll();
            }
        });

    }

    /**
     * This class will get the latest update from the
     * SwedishRadio class, calls it via a EventManager.
     */
    private void updateChannels(){
        Map <String, ArrayList<String>> categoryMap =
                eventManager.getChannelNames();
        /* Creates a new Menu for each category and creates
        *  JMenuItems for each channel under that category. */
        for(Map.Entry<String, ArrayList<String>> entry :
                categoryMap.entrySet()){

            String category = entry.getKey();
            List<String> channels= entry.getValue();
            JMenu categoryMenu = new JMenu(category);

            for(String channel: channels){
                JMenuItem channelItem = new JMenuItem(channel);
                channelItem.addActionListener(this::loadChannel);
                categoryMenu.add(channelItem);
            }

            this.add(categoryMenu);
        }
    }

    /**
     * Action listener for each button.
     * @param e ActionEvent
     */
    private void loadChannel(ActionEvent e){
        JMenuItem button = (JMenuItem) e.getSource();
        eventManager.showChannelTableau(button.getText());
    }


}
