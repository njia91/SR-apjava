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

    /**
     * Used to update the radio information.
     * @return String containing information about the update.
     */
    String update();

    /**
     * Used to retrieve channel information, given the channel name.
     * @param name String
     * @return ChannelInformation
     * @throws IOException is  thrown if something went wrong wile
     *                     reading channel information.
     */
    ChannelInformation retrieveChannelInfo(String name) throws IOException;

    /**
     * Gets an HashMap were each channel category has a key in the HashMap.
     * The value is a list of all the channels that falls
     * under that category.
     * @return Map<String, ArrayList<String>>
     */
    Map<String, ArrayList<String>> getChannelByCategory();

}
