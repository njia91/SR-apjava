package Controller;

import SwedishRadioInfo.ChannelInformation;
import SwedishRadioInfo.ProgramInformation;

import java.nio.channels.Channel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Michael Andersson
 */
public interface Controller {

    void updateChannelInformation();


    ChannelInformation getChannelInfo(String name);

    Map<String, ArrayList<String>> getCategories();

}
