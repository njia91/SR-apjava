package Controller;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author Michael Andersson
 */
public interface EventController {


    void update();

    void showChannelTableau(String name);


    Map<String, ArrayList<String>> getChannelNames();



}