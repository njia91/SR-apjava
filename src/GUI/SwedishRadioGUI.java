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
    private TableauPanel programTableau;
    private CardLayout layout;
    private ProgramDescription programDescription;
    private LoadingScreen loadingScreen;

    private static String START = "1";
    private static String PROGRAM = "2";
    private static String PROGRAMDESCRIPTION = "3";
    private static String LOADING = "4";

    public SwedishRadioGUI(StartScreen startScreen,
                           TableauPanel programTableau,
                           ProgramDescription programSpecifics,
                           LoadingScreen loadingScreen){

        super("Sveriges Radio");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setPreferredSize(new Dimension(600, 600));
        this.setLayout(new BorderLayout());
        this.startScreen = startScreen;
        this.programTableau = programTableau;
        this.programDescription = programSpecifics;
        this.loadingScreen = loadingScreen;
        this.startScreen.setPreferredSize(this.getPreferredSize());
        this.programTableau.setPreferredSize(this.getPreferredSize());
        this.loadingScreen.setPreferredSize(this.getPreferredSize());

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
        panelCont.add(loadingScreen, LOADING);
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
        layout.show(panelCont, PROGRAMDESCRIPTION);


    }

    public void returnToChannelTableau() {
        layout.show(panelCont, PROGRAM);
    }

    public void loadLoadingScreen(){
        layout.show(panelCont, LOADING);
    }

    public void loadStartScreen(){
        layout.show(panelCont, START);
    }

}
