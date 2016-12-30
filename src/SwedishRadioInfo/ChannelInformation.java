package SwedishRadioInfo;

import java.net.URL;
import java.util.List;

/**
 * Created by mian on 2016-12-28.
 */
public class ChannelInformation{

    private String name;
    private int id;
    private URL image;
    private URL liveAudio;
    private String channelType;
    private String schedule;
    private List<ProgramInformation> programInfo;



    public ChannelInformation(String name, int id, URL image,
                              URL liveAudio, String channelType,
                              String schedule) {
        this.name = name;
        this.id = id;
        this.image = image;
        this.liveAudio = liveAudio;
        this.channelType = channelType;
        this.schedule = schedule;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public URL getImage() {
        return image;
    }

    public URL getLiveAudio() {
        return liveAudio;
    }

    public String getChannelType() {
        return channelType;
    }

    public String getSchedule() {
        return schedule;
    }

    public List<ProgramInformation> getProgramInfo() {
        return programInfo;
    }

    public void setProgramInfo(List<ProgramInformation> programInfo) {
        this.programInfo = programInfo;
    }
}
