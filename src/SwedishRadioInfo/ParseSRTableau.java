package SwedishRadioInfo;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * This class will parse an given URL, leading to an XML-document of
 * Swedish radios channel list. It will store the information of each
 * channel in a object of type ProgramInformation. Later on the
 * another function, parseChannelTableau() can be called with the
 * list of channels as parameters. This function will parse each
 * channels program schedule and store information about each
 * program within a 12 hour span.
 *
 * It uses DOM to parse the XML.
 *
 * @version 1.0
 * @author Michael Andersson (mian)
 */
public class ParseSRTableau {

    private List<ChannelInformation> channelInfo;


    /**
     * Given an URL to SR API this class will parse the URL,
     * which should be an XML, and store information about each
     * channel in a list.
     * @param srURL String
     * @return List<ChannelInformation>
     * @throws IOException In case something went wrong with the parsning or
     *                     the connection to URL.
     */
    public List<ChannelInformation> parseChannels(String srURL) throws IOException {
        this.channelInfo = new ArrayList<>();
        Element rootNode;
        /* Open and parse XML-file */
        Document xmlChannels = createDOMTree(openURL(srURL).openStream());
        NodeList nList = xmlChannels.getElementsByTagName("sr");
        rootNode = (Element) nList.item(0);
        parseChannels((Element) rootNode.getElementsByTagName
                ("channels").item(0));

        return channelInfo;
    }

    /**
     * This method will parse the tableau for an specific channel.
     * @param cInfo ChannelInfomation
     * @throws IOException is thrown is something went wrong.
     */
    public void parseChannelTableau(ChannelInformation cInfo) throws IOException {
                /* Setting upp yesterdays and tomorrows date.
        This for retrieving the correct date for the channel tableau,*/
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
        System.out.println(today);
        Element scheduleRoot;

        /* Parse and gets information for all program
            episodes for the given channel */


        List<ProgramInformation> programInfo = new ArrayList<>();
        //Checks the tableau for channel for the surrounding dates.
        for(int i = 0; i < 3; i++){

            if(cInfo.getSchedule() != null) {


                /* Adds a date and sets pagination to false before
                   * opening the URL */
                Document programTableau = createDOMTree(openURL(
                            cInfo.getSchedule() +(surroundingDates[i])
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
        cInfo.setProgramInfo(programInfo);

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

        Date episodeDate_Start = null;
        Date episodeDate_End = null;
        Date currentDate = new Date();
        URL image;

        DateFormat formatter = new SimpleDateFormat("yy-MM-dd'T'HH:mm:ss'Z'");
        NodeList episodeList = schedule.
                getElementsByTagName("scheduledepisode");

        /* Parses the information about each episode. */
        for(int i = 0; i < episodeList.getLength(); i++){
            Element episode = (Element) episodeList.item(i);
            title = episode.getElementsByTagName
                    ("title").item(0).getTextContent();

            try {
                description = episode.getElementsByTagName
                        ("description").item(0).getTextContent();
            } catch (NullPointerException e){
                description = null;
            }

            /* Sometimes an episodes does not have an
                ImageURL tag in  the XML.*/
            try {
                image = openURL(episode.getElementsByTagName
                        ("imageurl").item(0).getTextContent());
            } catch (NullPointerException e){
                image = null;
            }

            startTime = episode.getElementsByTagName("starttimeutc").
                    item(0).getTextContent();
            endTime = episode.getElementsByTagName("endtimeutc").
                    item(0).getTextContent();
            try {
                episodeDate_Start = formatter.parse(startTime);
                episodeDate_End = formatter.parse(endTime);

            } catch (ParseException e) {
                System.err.println("Invalid Date format.");
                System.exit(1);
            }
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
     * This method will parse though all channels and the information
     * in a list. Some channels are missing some information, these
     * variables will be put to null.
     * @param channels Element from the DOM tree
     */
    private void parseChannels(Element channels){
        NodeList channelList = channels.getElementsByTagName("channel");
        String name;
        URL image;
        String channelType;
        String schedule;

        /* For each channel in the XLM, parses it and stores it
        *  in an object of type ChannelInformation.
        *  Some channels are missing certain elements, these
        *  variables will be set to null.*/
        for(int i = 0; i < channelList.getLength(); i++){
            Element channel = (Element) channelList.item(i);
            name = channel.getAttribute("name");

            try{
                image = openURL(channel.getElementsByTagName("image").
                        item(0).getTextContent());
            } catch (NullPointerException e){
                image = null;
            }

            try{
            channelType = channel.getElementsByTagName
                    ("channeltype").item(0).getTextContent();
            } catch (NullPointerException e){
                channelType = null;
            }

            try {
                schedule = channel.getElementsByTagName
                        ("scheduleurl").item(0).getTextContent();
            }catch (NullPointerException e){
                schedule = null;
            }
            channelInfo.add(new ChannelInformation(name, image,
                    channelType, schedule));
        }
    }

    /**
     * Will open an URL from a string.
     * @param srURL String
     * @return URL
     */
    private URL openURL(String srURL){
        try {
            return new URL(srURL);
        } catch (MalformedURLException e) {
            return null;
        }
    }

    /**
     * Will parse a given XML and return a DOM-tree structure.
     * @param input InputStream
     * @return Document
     * @throws IOException If an error occurred when opening XML-file.
     */
    private Document createDOMTree(InputStream input) throws IOException {
        Document doc;
        try{
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(input);
            doc.getDocumentElement().normalize();
        }
        catch (ParserConfigurationException | IOException
                | org.xml.sax.SAXException e) {
            throw new IOException("Can not open XML");
        }
        return doc;

    }

}
