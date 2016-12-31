package Controller;

import SwedishRadioInfo.ProgramInformation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Michael Andersson
 */
public interface Controller {

    void updateChannelInformation();


    List<ProgramInformation> getChannelTableau(String name);

    Map<String, ArrayList<String>> getCategories();

}
