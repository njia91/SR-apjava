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
        this.srURL = srURL;
        update();

    }

    public String update(){
        System.out.println(Thread.currentThread());
        List<ChannelInformation> tempList;
        srParser = new ParseSRTableau();
        try {
            tempList = srParser.parseChannels(this.srURL);
        } catch (IOException e) {
            return "Något fel: Kunde ej uppdatera kanal tablån. "
                    + "Se över din internetanslutning.";
        }
        this.channelInfo = tempList;
        channelInfo.sort((ChannelInformation c1, ChannelInformation c2)
                -> c1.getName().compareTo(c2.getName()));
        sortChannelsByCategory();
        return "Framgång: Kanaltablån är nu uppdaterad.";


    }

    public ChannelInformation retrieveChannelInfo(String name)
            throws IllegalArgumentException, IOException {
        for(ChannelInformation cInfo: channelInfo){
            if(cInfo.getName().equals(name)){
                srParser.parseChannelTableau(cInfo);
                cInfo.getProgramInfo().sort((ProgramInformation d1, ProgramInformation d2)
                        -> d1.getEpisodeDate_Start().compareTo(d2.getEpisodeDate_Start()));
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
