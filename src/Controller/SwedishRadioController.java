package Controller;


import SwedishRadioInfo.ProgramInformation;
import SwedishRadioInfo.SwedishRadio;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Michael Andersson
 */
public class SwedishRadioController {

    private SwedishRadio sr;

    public SwedishRadioController(SwedishRadio sr){
        this.sr = sr;
    }

    public synchronized void updateChannelInformation(){
        sr.update();
    }

    public synchronized List<ProgramInformation> getChannelTableau(String name){
        return sr.retrieveChannelTableau(name);
    }

    public synchronized Map<String, ArrayList<String>> getCategories(){
        return sr.getChannelByCategory();
    }

}
