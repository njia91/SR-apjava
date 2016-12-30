package SwedishRadioInfo;

import java.io.IOException;
import java.util.List;

/**
 * Created by mian on 2016-12-28.
 *
 */
public class SwedishRadio implements RadioInformation {

    private String srURL;
    private ParseSRTableau srParser;
    private List<ChannelInformation> channelInfo;

    public SwedishRadio(String srURL){
        srParser = new ParseSRTableau();
        this.srURL = srURL;
    }

    public String update(){
        try {
            channelInfo = srParser.parseChannels(this.srURL);
            srParser.parseChannelTableau(channelInfo);
        } catch (IOException e) {
            return "Could not connect to the SR API. Please" +
                    " check your internet connection.";
        }

        return "Successfully updated SR Channel Tableau.";


    }

    public List<ChannelInformation> getChannelList(){
        return channelInfo;
    }
}
