import Controller.GUIEventManager;
import Controller.SwedishRadioController;
import GUI.*;

import SwedishRadioInfo.SwedishRadio;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

/**
 *
 * Simple program that shows SR channel tableau.
 *
 * @author Michael Andersson
 * @version 4 January 2017
 */
public class SRMain {

    public static void main(String[] args) {

        Dimension d = new Dimension(600,600);
        StartScreen startScreen = new StartScreen(d);
        TableauPanel programTableau = new TableauPanel(d);
        ProgramDescription programSpecifics = new ProgramDescription(d);
        LoadingScreen loadingScreen = new LoadingScreen(d);


        SwedishRadio sr = new SwedishRadio(
                "http://api.sr.se/api/v2/channels?pagination=false");
        SwedishRadioController srCtrl = new SwedishRadioController(sr);
        SwedishRadioGUI gui = new SwedishRadioGUI(startScreen,
                programTableau, programSpecifics, loadingScreen);

        GUIEventManager eventManager = new GUIEventManager(srCtrl, gui);
        programTableau.setEventController(eventManager);
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(new Alternativ(eventManager));
        menuBar.add(new Kanaler(eventManager));

        gui.setJMenuBar(menuBar);
        gui.setPreferredSize(d);


        try {
            SwingUtilities.invokeAndWait(() ->{
                    gui.setVisible(true);
                    System.out.println("Avslutar");
        });
        } catch (InterruptedException | InvocationTargetException e) {
            e.printStackTrace();
        }


    }
}
