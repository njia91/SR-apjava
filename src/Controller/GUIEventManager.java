package Controller;

import GUI.ChannelLibrary;
import GUI.ProgramRenderer;
import GUI.SwedishRadioGUI;
import SwedishRadioInfo.ChannelInformation;
import SwedishRadioInfo.ProgramInformation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Michael Andersson
 */
public class GUIEventManager implements EventController {

    private SwedishRadioController sr;
    private SwedishRadioGUI gui;
    private ProgramRenderer programRenderer;
    private ChannelInformation currentChannel;
    private ScheduledExecutorService scheduledPool;


    public GUIEventManager(SwedishRadioController sr,
                           SwedishRadioGUI gui){
        this.sr = sr;
        this.gui = gui;
        programRenderer = new ProgramRenderer(this);
        this.scheduledPool = Executors.newScheduledThreadPool(2);
        setupAutomaticUpdateThread();
    }

    public void update(){

        SwingUtilities.invokeLater(() ->gui.loadLoadingScreen());

        scheduledPool.submit(() -> {
            String message = sr.updateChannelInformation();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException ignored){

            }
            SwingUtilities.invokeLater(() -> {
                gui.loadStartScreen();
                gui.setStatusBarText(message);
            });
        });

        System.out.println("I EventMaanger");


    }

    public void showChannelTableau(String name){

        try {
            currentChannel = sr.getChannelInfo(name);
            ChannelLibrary tableModel = new ChannelLibrary(currentChannel.getProgramInfo());

            gui.loadProgramTableau(currentChannel.getImage(), tableModel, programRenderer);
        } catch (IOException e) {
            //Load Not found IMAGE
        }
    }




    public void showProgramInfo(ProgramInformation pInfo){
        gui.loadProgramDescription(pInfo, createGoBackButtonListener());
        return;


    }

    public Map<String, ArrayList<String>> getChannelNames(){
        return sr.getCategories();
    }

    private ActionListener createGoBackButtonListener(){

        return e -> gui.returnToChannelTableau();
    }


    private void setupAutomaticUpdateThread(){
        scheduledPool.schedule(() -> {
            update();
            setupAutomaticUpdateThread();
        },
                1, TimeUnit.HOURS);
    }



    public void shutDownThreadPool(){
        scheduledPool.shutdown();
    }


}
