package SwedishRadioInfo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * Given an ChannelInformation this class with parse
 * givens channels tableau and store it in the given class.
 *
 * It will parse the tableau within a 12-hour interval from current date.
 *
 * @author Michael Andersson
 * @version 4 January 2017
 */
public class XMLParseTableau {


    /**
     * This method will parse the tableau for an specific channel.
     * @param url Webadress to channel tableau
     * @throws IOException is thrown is something went wrong.
     */
    public List<ProgramInformation> parseChannelTableau(String url)
            throws IOException {
                /* Setting upp yesterdays and tomorrows date.
        This for retrieving the correct date for the channel tableau,*/
        Element scheduleRoot;
        String[] surroundingDates = setupURL(url);
        /* Parse and gets information for all program
            episodes for the given channel */
        List<ProgramInformation> programInfo = new ArrayList<>();
        //Checks the tableau for channel for the surrounding dates.
        for(int i = 0; i < 3; i++){
            if(url != null) {
                /* Adds a date and sets pagination to false before
                   * opening the URL */
                Document programTableau = DomHelper.createDOMTree(
                        URLHelp.openURL(surroundingDates[i]
                                +"&pagination=false").openStream());
                NodeList nList = programTableau.
                        getElementsByTagName("sr");

                scheduleRoot = (Element) nList.item(0);
                parseProgram((Element) scheduleRoot.getElementsByTagName
                        ("schedule").item(0), programInfo);
            }else{
                throw new IOException("Can't find a channel tableau");
            }
        }
        return programInfo;
    }

    /**
     * This method will setup the URL for the parsing.
     * It will create and setup three different strings with
     * yesterdays, todays and tomorrows date.
     *
     * These dates will be concatenating to an URL.
     * @return String of surroundingDates
     */
    public String[] setupURL(String url ){
        DateFormat dtf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        String yesterday = dtf.format(cal.getTime());
        cal.add(Calendar.DATE, +1);
        String today = dtf.format(cal.getTime());
        cal.add(Calendar.DATE, +1);
        String tomorrow = dtf.format(cal.getTime());
        String[] surroundingDates = new String[3];
        surroundingDates[0] = "&date=" + yesterday;
        surroundingDates[1] = "&date=" + today;
        surroundingDates[2] = "&date=" + tomorrow;
        for(int i = 0; i < 3; i++){
            surroundingDates[i] = url + surroundingDates[i];
        }
        return surroundingDates;
    }

    /**
     *  This method will parse the the schedule tableau for
     *  each channel. It will add the information in the list
     *  given from the channel object.
     * @param schedule Element from the DOM tree.
     * @param programInfo List<ProgramInformation>
     */
    private void parseProgram(Element schedule,
                              List<ProgramInformation> programInfo){
        String title;
        String startTime;
        String endTime;
        String description;
        Date episodeDate_Start;
        Date episodeDate_End;
        Date currentDate = new Date();
        URL image;
        NodeList episodeList = schedule.
                getElementsByTagName("scheduledepisode");

        /* Parses the information about each episode. */
        for(int i = 0; i < episodeList.getLength(); i++){
            Element episode = (Element) episodeList.item(i);
            title = DomHelper.getElementInfo("title", episode);

            description = DomHelper.getElementInfo(
                    "description",episode);
            image = URLHelp.openURL(DomHelper.getElementInfo(
                    "imageurl", episode));

            startTime = DomHelper.getElementInfo(
                    "starttimeutc", episode);
            endTime = DomHelper.getElementInfo(
                    "endtimeutc", episode);

            episodeDate_Start = changeTimeZone(startTime);
            episodeDate_End = changeTimeZone(endTime);

            /* If episode is within date, keep it in the list.*/
            if (checkTimeInterval(episodeDate_Start, currentDate)){
                programInfo.add(new ProgramInformation(title,
                        description, image, episodeDate_Start,
                        episodeDate_End));
            }
        }
    }

    /**
     * This method will check if an episode plays within
     * twelve hours of current date and time.
     * @param episodeDate Date
     * @param currentDate Date
     * @return boolean
     */
    private boolean checkTimeInterval(Date episodeDate,
                                      Date currentDate){

        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);

        cal.add(Calendar.HOUR_OF_DAY, -12);
        Date twelveHoursBefore = cal.getTime();

        cal.add(Calendar.HOUR_OF_DAY, +24);
        Date twelveHoursAfter = cal.getTime();

        if ((episodeDate.after(twelveHoursBefore) &&
                episodeDate.before(twelveHoursAfter))){
            return true;
        }
        return false;
    }

    /**
     * Formatting the date from UTC to Sweden's timezone.
     * @param strDate UTC date
     * @return Date
     */
    private Date changeTimeZone(String strDate){
        DateFormat formatter = new SimpleDateFormat("yy-MM-dd'T'HH:mm:ss'Z'");
        formatter.setTimeZone(TimeZone.getTimeZone("Sweden"));
        Date date = null;
        try {
            date = formatter.parse(strDate);
        } catch (ParseException e) {
            System.out.println("Invalid Date format.");
            System.exit(1);
        }
        Calendar cal = new GregorianCalendar();
        cal.setTimeZone(TimeZone.getTimeZone("Etc/GMT+1"));
        cal.setTime(date);
        return cal.getTime();
    }



}
