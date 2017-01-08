package Controller;


import SwedishRadioInfo.ChannelInformation;
import SwedishRadioInfo.RadioInformation;
import SwedishRadioInfo.SwedishRadio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;



/**
 *
 * This class works as a controller between the GUI events
 * and RadioInformation. All methods in this class
 * is synchronized to prevent race conditions.
 *
 * @author Michael Andersson
 * @version 4 January 2017
 */
public class SwedishRadioController implements RadioInformation{

    private RadioInformation sr;

    /**
     * Constructor for SwedishRadioController
     * @param sr RadioInformation
     */
    public SwedishRadioController(SwedishRadio sr){
        this.sr = sr;
    }

    /**
     *  Updates channel information
     * @return String
     */
    public synchronized String update(){

        return sr.update();
    }

    /**
     *  Gets channel information from RadioInformation
     * @param name name of channel
     * @return The channelInformation for a specific channel
     * @throws IOException if something went wrong parsing xml.
     * @throws IllegalArgumentException if channel don't exist
     */
    public synchronized ChannelInformation retrieveChannelInfo(String name)
            throws IOException, IllegalArgumentException {
        return sr.retrieveChannelInfo(name);
    }

    /**
     *  Gets a HashMap were each channel is sorted by
     *  category from RadioInformation.
     * @return Map<String, ArrayList<String>>
     */
    public synchronized Map<String, ArrayList<String>> getChannelByCategory(){
        return sr.getChannelByCategory();
    }

}
