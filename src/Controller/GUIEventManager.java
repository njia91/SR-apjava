package Controller;

import GUI.SwedishRadioGUI;
import SwedishRadioInfo.ChannelInformation;
import SwedishRadioInfo.SwedishRadio;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author Michael Andersson
 */
public class GUIEventManager implements EventController {

    private SwedishRadioController sr;
    private SwedishRadioGUI gui;

    public GUIEventManager(SwedishRadioController sr,
                           SwedishRadioGUI gui){
        this.sr = sr;
        this.gui = gui;
    }

    public void update(){

    }

    public void showChannelTableau(String name){
        ChannelInformation cInfo = sr.getChannelInfo(name);
        gui.loadProgamTableau(cInfo.getImage());
    }


    public Map<String, ArrayList<String>> getChannelNames(){
        return sr.getCategories();
    }



}
