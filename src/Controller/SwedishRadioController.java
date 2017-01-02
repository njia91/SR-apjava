package Controller;


import SwedishRadioInfo.ChannelInformation;
import SwedishRadioInfo.SwedishRadio;

import java.io.IOException;
import java.util.ArrayList;
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
    public synchronized String updateChannelInformation(){
        return sr.update();
    }

    /**
     *
     * @param name
     * @return
     */
    public synchronized ChannelInformation getChannelInfo(String name)
            throws IOException, IllegalArgumentException {
        return sr.retrieveChannelInfo(name);
    }

    /**
     *
     * @return
     */
    public synchronized Map<String, ArrayList<String>> getCategories(){
        return sr.getChannelByCategory();
    }

}
