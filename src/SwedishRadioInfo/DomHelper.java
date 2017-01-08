package SwedishRadioInfo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

/**
 * This class contains static method which are used when traversing a
 * DOM tree structore.
 *
 * One method creates a DOM document, and other reads Elements text content.
 * and return a DOMTree document.
 *
 * @author Michael Andersson
 * @version 8 Januray 2017
 */
public class DomHelper {




    /**
     * Will parse a given XML and return a DOM-tree structure.
     * @param input InputStream
     * @return Document
     * @throws IOException If an error occurred when opening XML-file.
     */
    public static Document createDOMTree(InputStream input) throws IOException {
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

    /**
     * Method will get text content from an element.
     * @param name Name of element
     * @param element Parent node
     * @return element content
     */
    public static String getElementInfo(String name, Element element){
        try{
            return element.getElementsByTagName(
                    name).item(0).getTextContent();
        }catch (NullPointerException e){
            return null;
        }
    }
}
