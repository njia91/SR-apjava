package GUI;

import SwedishRadioInfo.ProgramInformation;
//import org.apache.ivy.core.event.EventManager;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.URL;


/**
 * The GUI for the Program. Uses CardLayout for displaying
 * different panels.
 *
 * @author Michael Andersson
 * @version 4 January 2017
 */
public class SwedishRadioGUI extends JFrame{

    private JPanel panelCont;
    private JPanel startScreen;
    private TableauPanel programTableau;
    private CardLayout layout;
    private ProgramDescription programDescription;
    private JPanel loadingScreen;
    private JLabel status;

    // Key Words for the CardLayout
    private static String START = "1";
    private static String PROGRAM = "2";
    private static String PROGRAMDESCRIPTION = "3";
    private static String LOADING = "4";


    /**
     * Constructor for SwedishRadioGUI
     * @param startScreen JPanel
     * @param programTableau TableauPanel
     * @param programSpecifics ProgramDescription
     * @param loadingScreen LoadingScreen
     */
    public SwedishRadioGUI(JPanel startScreen,
                           TableauPanel programTableau,
                           ProgramDescription programSpecifics,
                           JPanel loadingScreen){

        super("Sveriges Radio");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.startScreen = startScreen;
        this.programTableau = programTableau;
        this.programDescription = programSpecifics;
        this.loadingScreen = loadingScreen;
        this.startScreen.setPreferredSize(this.getPreferredSize());
        this.programTableau.setPreferredSize(this.getPreferredSize());
        this.loadingScreen.setPreferredSize(this.getPreferredSize());
        setUpStatusBar();

        setUpCardLayout();
        layout.show(panelCont, START);
        super.pack();
        this.setLocationRelativeTo(null);
        status.setText("Använd menyn i högra hörnet för att välja kanal.");
    }


    /**
     * Sets up the CardLayout
     */
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

    /**
     * Loads the program tableau. Passes these parameters on to TableauPanel.
     * @param imgURL URL
     * @param programInfoModel ChannelLibrary
     */
    public void loadProgramTableau(URL imgURL, ChannelLibrary programInfoModel){
        setStatusBarText("Klicka på en rad för att visa mer information.");
        programTableau.setImage(imgURL);
        programTableau.setTable(programInfoModel);
        layout.show(panelCont, PROGRAM);
    }

    /**
     * Lads the program description. Passes on these parameters to the
     * ProgramDescription panel.
     * @param pInfo ProgramInfo
     * @param listener ActionListener
     */
    public void loadProgramDescription(ProgramInformation pInfo,
                                       ActionListener listener){
        setStatusBarText("Klicka på knappen för att återgå till tablån.");
        programDescription.setupProgramInfo(pInfo, listener);
        layout.show(panelCont, PROGRAMDESCRIPTION);


    }

    /**
     * Tells gui to return to channelTableau.
     */
    public void returnToChannelTableau() {
        setStatusBarText("Klicka på en rad för att visa mer information.");
        layout.show(panelCont, PROGRAM);
    }

    /**
     * Tells gui to show loading screen
     */
    public void loadLoadingScreen(){
        setStatusBarText("Vänligen vänta. Kanaltablån uppdateras...");
        layout.show(panelCont, LOADING);
    }

    /**
     * Tells gui to show start screen
     */
    public void loadStartScreen(){
        status.setText("Använd menyn i högra hörnet för att välja kanal.");
        this.layout.show(panelCont, START);

    }

    /**
     * Sets the StatusBar text
     * @param message String
     */
    public void setStatusBarText(String message){
        status.setText(message);
    }


    /**
     * Sets the state for KanalMenu, can disable or enable it.
     * @param state Boolean
     */
    public void setStateForKanalMenu(Boolean state){
        JMenuBar bar = this.getJMenuBar();
        for( int i = 0; i < bar.getMenuCount(); i++){
            JMenu menu = bar.getMenu(i);
            if(menu instanceof Kanaler){
                menu.setEnabled(state);
            }
        }
    }

    /**
     * Setup the statusbar.
     */
    private void setUpStatusBar(){
        JPanel statusBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusBar.setBorder(new CompoundBorder(
                new LineBorder(Color.DARK_GRAY),
                new EmptyBorder(4,4,4,4)));
        status = new JLabel();
        statusBar.add(status);
        this.getContentPane().add(statusBar, BorderLayout.SOUTH);
    }


}
