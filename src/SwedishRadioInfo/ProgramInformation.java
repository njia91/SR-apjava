package SwedishRadioInfo;

import kotlin.reflect.jvm.internal.impl.descriptors.EffectiveVisibility;

import java.net.URL;
import java.util.Date;

/**
 * Wrapper class. Stores information about a program.
 */
public class ProgramInformation {

    private String title;
    private String description;
    private URL image;
    private Date episodeDate_Start;
    private Date episodeDate_End;



    public ProgramInformation(String title, String description,
                              URL image, Date episodeDate_Start,
                              Date episodeDate_End ) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.episodeDate_Start = episodeDate_Start;
        this.episodeDate_End = episodeDate_End;
    }

    public String getTitle() {
        return title;
    }


    public String getDescription() {
        return description;
    }

    public URL getImage() {
        return image;
    }



    public Date getEpisodeDate_Start() {
        return episodeDate_Start;
    }

    public Date getEpisodeDate_End() {
        return episodeDate_End;
    }
}
