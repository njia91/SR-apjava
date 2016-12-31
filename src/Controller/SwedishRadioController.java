package Controller;


import SwedishRadioInfo.ProgramInformation;
import SwedishRadioInfo.SwedishRadio;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Michael Andersson
 */
public class SwedishRadioController implements Controller{

    private SwedishRadio sr;

    /**
     *
     * @param sr
     */
    public SwedishRadioController(SwedishRadio sr){
        this.sr = sr;
    }

    /**
     *
     */
    public synchronized void updateChannelInformation(){
        sr.update();
    }

    /**
     *
     * @param name
     * @return
     */
    public synchronized List<ProgramInformation> getChannelTableau(String name){
        return sr.retrieveChannelTableau(name);
    }

    /**
     *
     * @return
     */
    public synchronized Map<String, ArrayList<String>> getCategories(){
        return sr.getChannelByCategory();
    }

}
