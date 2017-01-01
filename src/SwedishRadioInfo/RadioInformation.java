package SwedishRadioInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Interface for Radioinformation.
 * Basic method to update the Information and
 * retrieve the list of channels.
 */
public interface RadioInformation {

    String update();

    ChannelInformation retrieveChannelInfo(String name) throws IOException;

    Map<String, ArrayList<String>> getChannelByCategory();

}
