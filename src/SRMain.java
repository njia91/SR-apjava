import Controller.GUIEventManager;
import Controller.SwedishRadioController;
import GUI.Alternativ;
import GUI.Kanaler;
import GUI.StartScreen;
import GUI.SwedishRadioGUI;

import SwedishRadioInfo.SwedishRadio;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Michael Andersson
 */
public class SRMain {

    public static void main(String[] args) {

        StartScreen startScreen = new StartScreen();

        SwedishRadio sr = new SwedishRadio(
                "http://api.sr.se/api/v2/channels?pagination=false");
        SwedishRadioController srCtrl = new SwedishRadioController(sr);
        SwedishRadioGUI gui = new SwedishRadioGUI(startScreen);

        GUIEventManager eventManager = new GUIEventManager(srCtrl, gui);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(new Alternativ(eventManager));
        menuBar.add(new Kanaler(eventManager));

        gui.setJMenuBar(menuBar);



        try {
            SwingUtilities.invokeAndWait(() ->
                    gui.setVisible(true)
            );
        } catch (InterruptedException | InvocationTargetException e) {
            e.printStackTrace();
        }


    }
}
