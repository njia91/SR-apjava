package SwedishRadioInfo;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 *
 * This class will parse an given URL, leading to an XML-document of
 * Swedish radios channel list. It will store the information of each
 * channel in a object of type ChannelInformation.
 *
 * It uses DOM to parse the XML.
 *
 * @version 1.0
 * @author Michael Andersson (mian)
 */
public class XMLParseChannels {

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
    public List<ChannelInformation> parseChannels(String srURL)
            throws IOException {
        this.channelInfo = new ArrayList<>();
        Element rootNode;
        /* Open and parse XML-file */
        Document xmlChannels = DomHelper.createDOMTree(
                URLHelp.openURL(srURL).openStream());
        NodeList nList = xmlChannels.getElementsByTagName("sr");
        rootNode = (Element) nList.item(0);
        parseChannels((Element) rootNode.getElementsByTagName
                ("channels").item(0));

        return channelInfo;
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

            image = URLHelp.openURL(DomHelper.getElementInfo("image", channel));
            channelType = DomHelper.getElementInfo("channeltype", channel);
            schedule = DomHelper.getElementInfo("scheduleurl", channel);
            channelInfo.add(new ChannelInformation(name, image,
                    channelType, schedule));
        }
    }



}
