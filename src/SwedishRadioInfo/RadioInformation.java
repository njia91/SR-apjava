package SwedishRadioInfo;

import java.util.List;

/**
 * Interface for Radioinformation.
 * Basic method to update the Information and
 * retrieve the list of channels.
 */
public interface RadioInformation {

    String update();

    List<ChannelInformation> getChannelList();

}
