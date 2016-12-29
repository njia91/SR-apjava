package SwedishRadioInfo;

import com.mockobjects.util.Null;
import jdk.internal.org.xml.sax.SAXException;
import org.apache.tools.ant.util.FileUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


import javax.swing.text.DateFormatter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by mian on 2016-12-28.
 *
 */
public class ParseSRTableau {

    private List<ChannelInformation> channelInfo;

    public ParseSRTableau() {

        channelInfo = new ArrayList<>();


    }


    public List<ChannelInformation> parseChannels(String srURL) throws IOException {

        Element rootNode;
        /* Open and parse XML-file */
        Document xmlChannels = createDOMTree(openURL(srURL).openStream());
        NodeList nList = xmlChannels.getElementsByTagName("sr");
        rootNode = (Element) nList.item(0);
        parseChannels((Element) rootNode.getElementsByTagName
                ("channels").item(0));

        return channelInfo;

    }

    public void parseChannelTableau(List<ChannelInformation> channelInfo) throws IOException {

        Element rootNode;
        /* Parse and gets information for all program
            episodes for the given channel */
        for(ChannelInformation cInfo: channelInfo){
            if(cInfo.getSchedule() != null) {
                System.out.println("Ny kanal...");
                List<ProgramInformation> programInfo = new ArrayList<>();

                Document programTableau = createDOMTree(openURL(
                        cInfo.getSchedule().toString()).openStream());
                NodeList nList = programTableau.getElementsByTagName("sr");
                rootNode = (Element) nList.item(0);
                parseProgram((Element) rootNode.getElementsByTagName
                        ("schedule").item(0), programInfo);
                cInfo.setProgramInfo(programInfo);
            }

        }

    }

    private void parseProgram(Element schedule, List<ProgramInformation> programInfo){
        String title;
        String startTime;
        String endTime;
        String description;
        String date;
        URL image;

        String temp;
        String[] str;

        NodeList episodeList = schedule.getElementsByTagName("scheduledepisode");

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
            temp = episode.getElementsByTagName("starttimeutc").
                    item(0).getTextContent();
            str = temp.split("T");
            date = str[0];
            str = str[1].split("Z");
            startTime = str[0];

            temp = episode.getElementsByTagName("endtimeutc").
                    item(0).getTextContent();
            str = temp.split("T");
            str = str[1].split("Z");
            endTime = str[0];


            if (checkTimeInterval(date, startTime)){
                programInfo.add(new ProgramInformation(title,
                        description, image, date, startTime, endTime));
            }



        }

    }

    private boolean checkTimeInterval(String date,
                                    String startTime){



        DateFormat formatter = new SimpleDateFormat("yy-MM-dd'T'HH:mm:ss");
        Date episodeDate = null;
        String strdate = date.concat("T" + startTime);


        try {
            episodeDate = formatter.parse(strdate);
        } catch (ParseException e) {
            System.err.println("Invalid Date format.");
            return false;
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(episodeDate);

        cal.add(Calendar.HOUR, -12);
        Date twelveHoursBefore = cal.getTime();

        cal.add(Calendar.HOUR, +24);
        Date twelveHoursAfter = cal.getTime();

        return (episodeDate.after(twelveHoursBefore) &&
                episodeDate.before(twelveHoursAfter));

    }

    private void parseChannels(Element channels){
        NodeList channelList = channels.getElementsByTagName("channel");
        String name;
        int id;
        URL image;
        URL liveAudio;
        String channelType;
        URL schedule;
        /* Setting upp yesterdays and tomorrows date.
        This for retrieving the correct date for the channel tableau,*/
        DateFormat dtf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        String yesterday = dtf.format(cal.getTime());
        cal.add(Calendar.DATE, +2);
        String tomorrow = dtf.format(cal.getTime());


        for(int i = 0; i < channelList.getLength(); i++){
            Element channel = (Element) channelList.item(i);
            name = channel.getAttribute("name");
            id = Integer.parseInt(channel.getAttribute("id"));
            image = openURL(channel.getElementsByTagName("image").
                    item(0).getTextContent());

            Element audioElement = (Element) channel.
                    getElementsByTagName("liveaudio").item(0);
            liveAudio = openURL(audioElement.
                    getElementsByTagName("url").item(0).getTextContent());

            /* Some channels don't have an URL to their schedule. */

            try {
                String scheduleUrl =channel.getElementsByTagName
                        ("scheduleurl").item(0).getTextContent();
                schedule = openURL(scheduleUrl + "&fromdate=" +
                        yesterday + "&todate=" + tomorrow
                        + "&pagination=false");
                System.out.println(schedule);
            }catch (NullPointerException e){
                schedule = null;
            }

            channelType = channel.getElementsByTagName
                    ("channeltype").item(0).getTextContent();

            channelInfo.add(new ChannelInformation(name, id, image,
                    liveAudio, channelType, schedule));

        }


    }

    private URL openURL(String srURL){
        try {
            return new URL(srURL);
        } catch (MalformedURLException e) {
            return null;
        }
    }

    /**
     * Will parse a given XML and return a DOM-tree stuctur.
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
