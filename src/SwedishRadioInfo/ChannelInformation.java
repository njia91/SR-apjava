package SwedishRadioInfo;

import java.net.URL;
import java.util.List;

/**
 * Wrapper class. Keeps information about a channel.
 * Its name, id, Image, type, URL to schedule and
 * a list of program information.
 *
 * @author Michael Andersson
 * @version 4 January 2017
 */
public class ChannelInformation{

    private String name;
    private URL image;
    private String channelType;
    private String schedule;
    private List<ProgramInformation> programInfo;


    /**
     * Constructor for ChannelInformation
     * @param name String
     * @param image URL
     * @param channelType String
     * @param schedule String
     */
    public ChannelInformation(String name, URL image,
                              String channelType,
                              String schedule) {
        this.name = name;
        this.image = image;
        this.channelType = channelType;
        this.schedule = schedule;
        programInfo = null;
    }

    /**
     * Getter for channel name.
     * @return String
     */
    public String getName() {
        return name;
    }


    /**
     * Getter for channel image.
     * @return URL
     */
    public URL getImage() {
        return image;
    }

    /**
     * Getter for channel type.
     * @return String
     */
    public String getChannelType() {
        return channelType;
    }

    /**
     * Getter for channel tableau.
     * @return String
     */
    public String getSchedule() {
        return schedule;
    }

    /**
     * Getter for a list of programinformation.
     * @return List<ProgramInformation>
     */
    public List<ProgramInformation> getProgramInfo() {
        return programInfo;
    }

    /**
     * Setter for ProgramInformation.
     * @param programInfo List<ProgramInformation>
     */
    public void setProgramInfo(List<ProgramInformation> programInfo) {
        this.programInfo = programInfo;
    }
}
