import GUI.SwedishRadioGUI;

import SwedishRadioInfo.SwedishRadio;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Michael Andersson
 */
public class SRMain {

    public static void main(String[] args) {


        SwedishRadio sr = new SwedishRadio("http://api.sr.se/api/v2/channels ");


        SwedishRadioGUI gui = new SwedishRadioGUI();

        try {
            SwingUtilities.invokeAndWait(() ->
                    gui.setVisible(true)
            );
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }
}
