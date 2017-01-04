package Controller;

import SwedishRadioInfo.ChannelInformation;
import SwedishRadioInfo.ProgramInformation;

import java.io.IOException;
import java.nio.channels.Channel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Interface for a controller.
 *
 * @author Michael Andersson
 * @version 4 January 2017
 */
public interface Controller {

    /**
     * Method for updating channel information
     * @return String of update information
     */
    String updateChannelInformation();

    /**
     * Gets channelinformation
     * @param name String
     * @return ChannelInformation
     * @throws IOException Something went wrong when reading XML-file
     * @throws IllegalArgumentException If channel do not exist.
     */
    ChannelInformation getChannelInfo(String name)
            throws IOException, IllegalArgumentException;

    /**
     * Returns HashMap contaning channel categories.
     * @return
     */
    Map<String, ArrayList<String>> getCategories();

}
