package Controller;

import SwedishRadioInfo.ProgramInformation;

import java.util.ArrayList;
import java.util.Map;

/**
 * Interface for EventController.
 * Handles event in a GUI.
 *
 * @author Michael Andersson
 * @version 4 January 2017
 */
public interface EventController {


    /**
     * Used to update information.
     */
    void update();

    /**
     * Showing the channel tableau.
     * @param name String
     */
    void showChannelTableau(String name);

    /**
     *  Return a HasMap with channels sorted by category.
     * @return
     */
    Map<String, ArrayList<String>> getChannelNames();

    /**
     * Shows information about a certain program.
     * @param index int
     */
    void showProgramInfo(int index);

}
