package SwedishRadioInfo;

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


    /**
     * Constructor for ProgramInformation.
     * @param title String
     * @param description String
     * @param image URL
     * @param episodeDate_Start Date
     * @param episodeDate_End Date
     */
    public ProgramInformation(String title, String description,
                              URL image, Date episodeDate_Start,
                              Date episodeDate_End ) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.episodeDate_Start = episodeDate_Start;
        this.episodeDate_End = episodeDate_End;
    }

    /**
     * Getter for program title.
     * @return String
     */
    public String getTitle() {
        return title;
    }

    /**
     * Getter for program description.
     * @return String
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter for program image.
     * @return URL
     */
    public URL getImage() {
        return image;
    }

    /**
     * Getter for episode start date.
     * @return Date
     */
    public Date getEpisodeDate_Start() {
        return episodeDate_Start;
    }

    /**
     * Getter for episode end date.
     * @return Date
     */
    public Date getEpisodeDate_End() {
        return episodeDate_End;
    }
}
