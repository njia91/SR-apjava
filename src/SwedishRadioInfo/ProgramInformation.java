package SwedishRadioInfo;

import java.net.URL;

/**
 * Wrapper class. Stores information about the each program.
 */
public class ProgramInformation {

    private String title;
    private int id;
    private String description;
    private URL image;
    private String date;
    private String start;
    private String end;
    private String channel;


    public ProgramInformation(String title, String description,
                              URL image, String date, String start,
                              String end) {
        this.title = title;
        this.description = this.description;
        this.image = image;
        this.date = date;
        this.start = start;
        this.end = end;
        this.channel = channel;
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

    public String getDate() {
        return date;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

}
