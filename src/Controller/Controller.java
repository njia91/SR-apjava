package Controller;

import SwedishRadioInfo.ChannelInformation;
import SwedishRadioInfo.ProgramInformation;

import java.io.IOException;
import java.nio.channels.Channel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Michael Andersson
 */
public interface Controller {

    String updateChannelInformation();


     ChannelInformation getChannelInfo(String name)
            throws IOException, IllegalArgumentException;

    Map<String, ArrayList<String>> getCategories();

}
