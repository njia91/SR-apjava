package Controller;


import SwedishRadioInfo.ChannelInformation;
import SwedishRadioInfo.RadioInformation;
import SwedishRadioInfo.SwedishRadio;

import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 *
 * This class works as a controller between the GUI events
 * and RadioInformation. All methods in this class
 * is synchronized to prevent race conditions.
 *
 * @author Michael Andersson
 * @version 4 January 2017
 */
public class SwedishRadioController implements Controller{

    private RadioInformation sr;

    /**
     * Constructor for SwedishRadioController
     * @param sr RadioInformation
     */
    public SwedishRadioController(SwedishRadio sr){
        this.sr = sr;
    }

    /**
     * Updates channel information.
     */
    public synchronized String updateChannelInformation(){

        return sr.update();
    }

    /**
     *  Gets channel information from RadioInformation
     * @param name name of channel
     * @return The channelInformation for a specific channel
     * @throws IOException if something went wrong parsing xml.
     * @throws IllegalArgumentException if channel don't exist
     */
    public synchronized ChannelInformation getChannelInfo(String name)
            throws IOException, IllegalArgumentException {
        return sr.retrieveChannelInfo(name);
    }

    /**
     *  Gets a HashMap were each channel is sorted by
     *  category from RadioInformation.
     * @return Map<String, ArrayList<String>>
     */
    public synchronized Map<String, ArrayList<String>> getCategories(){
        return sr.getChannelByCategory();
    }

}
