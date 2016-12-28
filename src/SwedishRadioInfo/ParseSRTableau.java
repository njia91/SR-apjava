package SwedishRadioInfo;

import jdk.internal.org.xml.sax.SAXException;
import org.apache.tools.ant.util.FileUtils;
import org.w3c.dom.Document;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import java.util.List;

/**
 * Created by mian on 2016-12-28.
 *
 */
public class ParseSRTableau {

    private URL srChannels;
    private Document doc;

    public ParseSRTableau(String srURL) {
        try {
            srChannels = new URL(srURL);
        } catch (MalformedURLException e) {
            System.err.println("Could not open URL to SR API");
            System.exit(1);
        }
    }




    public List<ChannelInformation> parseChannels() throws IOException {

        int t;
        try{
            InputStream input = srChannels.openStream();


            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(input);
            doc.getDocumentElement().normalize();
        }
        catch (ParserConfigurationException | IOException
                | org.xml.sax.SAXException e) {
            throw new IOException("Can not open XML");

        }

        return null;

    }

    public void parseChannelTableau(List<ChannelInformation> cInfo){


    }

}
