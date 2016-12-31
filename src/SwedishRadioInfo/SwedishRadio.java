package SwedishRadioInfo;

import java.io.IOException;
import java.util.*;

/**
 * @author Michael Andersson
 *
 */
public class SwedishRadio implements RadioInformation {

    private String srURL;
    private ParseSRTableau srParser;
    private List<ChannelInformation> channelInfo;
    private Map<String, ArrayList<String>> channelByCategory;

    public SwedishRadio(String srURL){
        srParser = new ParseSRTableau();
        this.srURL = srURL;
        update();

    }

    public String update(){
        List<ChannelInformation> tempList;
        try {
            tempList = srParser.parseChannels(this.srURL);
            srParser.parseChannelTableau(tempList);
        } catch (IOException e) {
            return "Could not connect to the SR API. Please" +
                    " check your internet connection.";
        }
        this.channelInfo = tempList;
        sortChannelsByCategory();
        return "Successfully updated SR Channel Tableau.";


    }

    public ChannelInformation retrieveChannelInfo(String name)
            throws IllegalArgumentException{
        for(ChannelInformation cInfo: channelInfo){
            if(cInfo.getName().equals(name)){
                return cInfo;
            }
        }
        throw new IllegalArgumentException("Invalid channel name");

    }

    public Map<String, ArrayList<String>> getChannelByCategory() {
        return channelByCategory;
    }

    /**
     *
     */
    private void sortChannelsByCategory(){
        channelByCategory = new HashMap<>();

        for(ChannelInformation cinfo : channelInfo){
            if (!channelByCategory.containsKey(cinfo.getChannelType())){
                ArrayList<String> category = new ArrayList<>();
                category.add(cinfo.getName());
                channelByCategory.put(cinfo.getChannelType(), category);
            }else{
                channelByCategory.get(cinfo.getChannelType()).
                        add(cinfo.getName());
            }
        }

        for(Map.Entry<String, ArrayList<String>> pair:
                channelByCategory.entrySet()){
            Collections.sort(pair.getValue());
        }
    }
}
