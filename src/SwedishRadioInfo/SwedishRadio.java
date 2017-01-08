package SwedishRadioInfo;

import java.io.IOException;
import java.util.*;

/**
 *
 * This class will work as a facade towards the SwedishRadioInfo package.
 * When told to it will update the channelInformation and it will
 * also return information about each channel and its tableau.
 *
 * @author Michael Andersson
 * @version 4 January 2017
 */
public class SwedishRadio implements RadioInformation {

    private String srURL;
    private XMLParseChannels channelParser;
    private List<ChannelInformation> channelInfo;
    private XMLParseTableau tableauParser;
    private Map<String, ArrayList<String>> channelByCategory;

    /**
     * Constructor for SwedishRadio
     * @param srURL URL to Swedish radio API
     */
    public SwedishRadio(String srURL){
        tableauParser = new XMLParseTableau();
        this.srURL = srURL;
       // update();

    }

    /**
     * This method will call SR API and update the
     * channel information. For each update, the method
     * will create a new object of type XMLParseChannels.
     * This is to prevent race conditions between threads.
     *
     * @return information about the parsing, if it was a success or not.
     */
    public String update(){
        List<ChannelInformation> tempList;
        channelParser = new XMLParseChannels();
        try {
            tempList = channelParser.parseChannels(this.srURL);
        } catch (IOException e) {
            return "Något fel: Kunde ej uppdatera kanal tablån. "
                    + "Se över din internetanslutning.";
        }
        this.channelInfo = tempList;
        channelInfo.sort(Comparator.comparing(ChannelInformation::getName));
        sortChannelsByCategory();
        return "Framgång: Kanaltablån är nu uppdaterad.";


    }

    /**
     *  Will return the given channels information.
     * @param name String
     * @return ChannelInformation of the given channel
     * @throws IllegalArgumentException if channel doesn't
     * exist
     * @throws IOException If something went wrong while parsing
     * the channel tableau.
     */
    public ChannelInformation retrieveChannelInfo(String name)
            throws IllegalArgumentException, IOException {
        for(ChannelInformation cInfo: channelInfo){
            if(cInfo.getName().equals(name)){
                /* Only parse channel tableau if
                 it hasn't already been parsed. */
                if(cInfo.getProgramInfo() == null) {
                    cInfo.setProgramInfo(tableauParser.
                            parseChannelTableau(cInfo.getSchedule()));
                    cInfo.getProgramInfo().sort(Comparator.comparing(
                            ProgramInformation::getEpisodeDate_Start));
                }
                return cInfo;
            }
        }
        throw new IllegalArgumentException("Invalid channel name");

    }

    /**
     * Gets an HashMap were each channel category has a key in the HashMap.
     * The value is a list of all the channels that falls
     * under that category.
     * @return Map<String, ArrayList<String>>
     */
    public Map<String, ArrayList<String>> getChannelByCategory() {
        return channelByCategory;
    }


    /**
     * Will connect each channel with a category.
     * Stores the information in a HashMap.
     */
    private void sortChannelsByCategory(){
        channelByCategory = new HashMap<>();

        // Connects each channel with a category.
        for(ChannelInformation cInfo : channelInfo){
            if (!channelByCategory.containsKey(cInfo.getChannelType())){
                ArrayList<String> category = new ArrayList<>();
                category.add(cInfo.getName());
                channelByCategory.put(cInfo.getChannelType(), category);
            }else{
                channelByCategory.get(cInfo.getChannelType()).
                        add(cInfo.getName());
            }
        }
        // Sorts the channels by name.
        for(Map.Entry<String, ArrayList<String>> pair:
                channelByCategory.entrySet()){
            Collections.sort(pair.getValue());
        }
    }
}
