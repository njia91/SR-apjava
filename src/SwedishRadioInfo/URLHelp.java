package SwedishRadioInfo;


import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Michael Andersson
 * @version 8 January 2017
 */
public class URLHelp {


    /**
     * Will open an URL from a string.
     * @param srURL String
     * @return URL
     */
    public static URL openURL(String srURL){
        try {
            return new URL(srURL);
        } catch (MalformedURLException e) {
            return null;
        }
    }
}
