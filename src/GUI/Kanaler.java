package GUI;


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
 * @author Michael Andersson
 */
public class Kanaler extends JMenu{

    private GUIEventManager eventManager;

    public Kanaler(GUIEventManager eventManager){
        super("Kanaler");
        this.eventManager = eventManager;
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


    private void updateChannels(){
        Map <String, ArrayList<String>> categoryMap =
                eventManager.getChannelNames();
        System.out.println(categoryMap.size());
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

    private void loadChannel(ActionEvent e){
        JMenuItem button = (JMenuItem) e.getSource();
        eventManager.showChannelTableau(button.getText());
    }


}
