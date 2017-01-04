package Controller;

import GUI.ChannelLibrary;
import GUI.ProgramRenderer;
import GUI.SwedishRadioGUI;
import SwedishRadioInfo.ChannelInformation;
import SwedishRadioInfo.ProgramInformation;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * This class handles all events in the program.
 * The class has an ScheduledExecutorService, that will
 * update the channel information after each hour.
 *
 *  When a task that takes long time(Updating), an other
 *  thread will be used. When it is done it will notify
 *  the GUI.
 *
 * @author Michael Andersson
 * @version 4 January 2017
 */
public class GUIEventManager implements EventController {

    private SwedishRadioController sr;
    private SwedishRadioGUI gui;
    private ChannelInformation currentChannel;
    private ScheduledExecutorService scheduledPool;
    private int updateInterval = 1;


    /**
     * Constructor for GUIEventManager
     *
     * @param sr SwedishRadio
     * @param gui SwedishRadioGUI
     */
    public GUIEventManager(SwedishRadioController sr,
                           SwedishRadioGUI gui){
        this.sr = sr;
        this.gui = gui;
        this.scheduledPool = Executors.newScheduledThreadPool(2);

        setupAutomaticUpdateThread();
    }


    /**
     * Will update the ChannelInformation.
     * The Swing thread will fist change to the loading screen
     * before submitting an order for the thread pool.
     *
     * When the thread is done updating it will use
     * SwingUtilities.invokeLater(), and tell the
     * GUI to change panel.
     */
    public void update(){

        gui.setStateForKanalMenu(false);
        gui.loadLoadingScreen();
        scheduledPool.submit(() -> {
            String message = sr.updateChannelInformation();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ignored){

            }
            SwingUtilities.invokeLater(() -> {
                gui.loadStartScreen();
                gui.setStatusBarText(message);
                gui.setStateForKanalMenu(true);
            });
        });
    }

    /**
     * Given the name of the channel, load the channel tableau.
     * @param name String
     */
    public void showChannelTableau(String name){
        scheduledPool.submit(() -> {
            try {
                currentChannel = sr.getChannelInfo(name);
                ChannelLibrary tableModel = new ChannelLibrary(
                        currentChannel.getProgramInfo());
                SwingUtilities.invokeLater(() -> gui.loadProgramTableau(
                        currentChannel.getImage(),
                        tableModel));
            } catch (IOException e) {
                SwingUtilities.invokeLater(() -> {
                    gui.setStatusBarText
                        ("Kan ej ladda kanaltablån. " +
                        "Internet problem eller är kanalen utan tablå");
                    gui.loadStartScreen();
                });
            }
        });

    }

    /**
     * Tells GUI to change panel and display program description.
     * @param index Index of episode.
     */
    public void showProgramInfo(int index){
        gui.loadProgramDescription(currentChannel.
                getProgramInfo().get(index), e -> gui.returnToChannelTableau());
    }

    /**
     *  Gets a HashMap of all channels connected to a specific
     *  category.
     * @return Map<String, ArrayList<String>>
     */
    public Map<String, ArrayList<String>> getChannelNames(){
        return sr.getCategories();
    }


    /**
     * Sets up the automatic update.
     */
    private void setupAutomaticUpdateThread(){
        scheduledPool.schedule(() -> {
            update();
            setupAutomaticUpdateThread();
        }, updateInterval, TimeUnit.HOURS);
    }



}
