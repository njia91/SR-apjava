package GUI;

import SwedishRadioInfo.ProgramInformation;
//import org.apache.ivy.core.event.EventManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.URL;


/**
 * @author Michael Andersson
 */
public class SwedishRadioGUI extends JFrame{

    private JPanel panelCont;
    private StartScreen startScreen;
    private ProgramTableau programTableau;
    private CardLayout layout;
    private ProgramDescription programDescription;

    private static String START = "1";
    private static String PROGRAM = "2";
    private static String PROGRAMDESCRIPTION = "3";

    public SwedishRadioGUI(StartScreen startScreen,
                           ProgramTableau programTableau,
                           ProgramDescription programSpecifics){

        super("Sveriges Radio");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setPreferredSize(new Dimension(600, 600));
        this.setLayout(new BorderLayout());
        this.startScreen = startScreen;
        this.programTableau = programTableau;
        this.programDescription = programSpecifics;
        this.startScreen.setPreferredSize(this.getPreferredSize());
        this.programTableau.setPreferredSize(this.getPreferredSize());
        setUpCardLayout();
        layout.show(panelCont, START);
        super.pack();
        this.setLocationRelativeTo(null);
    }


    private void setUpCardLayout(){
        panelCont = new JPanel();
        layout = new CardLayout();
        panelCont.setLayout(layout);
        panelCont.add(startScreen, START);
        panelCont.add(programTableau, PROGRAM);
        panelCont.add(programDescription, PROGRAMDESCRIPTION);
        this.add(panelCont);
    }

    public void loadProgramTableau(URL imgURL, ChannelLibrary programInfoModel,
                                  ProgramRenderer renderer){
        programTableau.setImage(imgURL);
        programTableau.setTable(programInfoModel, renderer);
        layout.show(panelCont, PROGRAM);
    }

    public void loadProgramDescription(ProgramInformation pInfo,
                                       ActionListener listener){

        programDescription.setUpProgramInfo(pInfo, listener);
        System.out.println("ASdasdasdasd");
        layout.show(panelCont, PROGRAMDESCRIPTION);


    }

    public void returnToChannelTableau() {
        layout.show(panelCont, PROGRAM);
    }

}
