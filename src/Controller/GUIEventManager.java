package Controller;

import GUI.ChannelLibrary;
import GUI.ProgramRenderer;
import GUI.SwedishRadioGUI;
import SwedishRadioInfo.ChannelInformation;
import SwedishRadioInfo.ProgramInformation;
import SwedishRadioInfo.SwedishRadio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author Michael Andersson
 */
public class GUIEventManager implements EventController {

    private SwedishRadioController sr;
    private SwedishRadioGUI gui;
    private ProgramRenderer programRenderer;
    private ChannelInformation currentChannel;


    public GUIEventManager(SwedishRadioController sr,
                           SwedishRadioGUI gui){
        this.sr = sr;
        this.gui = gui;
        programRenderer = new ProgramRenderer(this);
    }

    public void update(){

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




    public void showProgramInfo(String name){
        System.out.println("SKRIVER UT!");
        System.out.println(name);
        for(ProgramInformation pInfo : currentChannel.getProgramInfo()){
            System.out.println(name);
            if (pInfo.getTitle().equals(name)){

                gui.loadProgramDescription(pInfo,
                        createGoBackButtonListener());
                return;
            }
        }

    }

    public Map<String, ArrayList<String>> getChannelNames(){
        return sr.getCategories();
    }

    private ActionListener createGoBackButtonListener(){

        return e -> gui.returnToChannelTableau();
    }





}
