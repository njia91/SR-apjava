package GUI;

import SwedishRadioInfo.ProgramInformation;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import java.net.URL;

/**
 * Extends JPanel and will display program description
 * and other specifics about a program.
 *
 * @author Michael Andersson
 * @version 4 January 2017
 */
public class ProgramDescription extends JPanel{


    private JPanel uppPanel = new JPanel();
    private JPanel middle = new JPanel();
    private JLabel picLabel;
    private JLabel description;
    private JLabel startDate;
    private JLabel endDate;
    private JLabel title;
    private JButton returnToTableau;


    /**
     * Constructor for ProgramDescription
     * @param d Dimension
     */
    public ProgramDescription(Dimension d){
        super();
        this.setLayout(new BorderLayout());
        this.uppPanel = new JPanel();
        this.middle = new JPanel();
        JPanel infoPanel = new JPanel();
        this.setPreferredSize(d);
        this.add(uppPanel, BorderLayout.NORTH);
        this.add(middle, BorderLayout.SOUTH);
        infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel,
                BoxLayout.Y_AXIS));
        uppPanel.add(infoPanel, BorderLayout.EAST);

        this.picLabel = new JLabel();
        this.description = new JLabel();
        this.startDate = new JLabel();
        this.endDate = new JLabel();
        this.title = new JLabel();

        this.returnToTableau = new JButton("Gå tillbaka till kanaltablån.");
        returnToTableau.setPreferredSize(new Dimension(400,50));

        Dimension space = new Dimension(0, 20);
        infoPanel.add(title);
        infoPanel.add(Box.createRigidArea(space));
        infoPanel.add(description);
        infoPanel.add(Box.createRigidArea(space));
        infoPanel.add(startDate);
        infoPanel.add(endDate);
        uppPanel.add(picLabel, BorderLayout.WEST);


    }


    /**
     * Sets up the information about the program.
     * @param pInfo ProgramInformation
     * @param listener ActionListener
     */
    public void setupProgramInfo(ProgramInformation pInfo,
                                 ActionListener listener){

        setupProgramImage(pInfo.getImage());
        setupProgramDescription(pInfo);
        setupReturnButton(listener);
        this.revalidate();
    }

    /**
     * Sets up programImage
     * @param imageURL URL
     */
    private void setupProgramImage(URL imageURL){
        URLImageLoader loader = new URLImageLoader();
        BufferedImage img = loader.openURLImage(imageURL);
        picLabel.setSize(this.getPreferredSize());
        Image scaledImg = img.getScaledInstance(picLabel.getWidth()/2,
                picLabel.getHeight() / 2, Image.SCALE_SMOOTH);
        picLabel.setIcon(new ImageIcon(scaledImg));
        picLabel.setHorizontalAlignment(JLabel.CENTER);
        picLabel.setVerticalAlignment(JLabel.CENTER);
        uppPanel.add(picLabel, BorderLayout.WEST);
    }


    /**
     * Sets up program description
     * @param pInfo ProgramInformation
     */
    private void setupProgramDescription(ProgramInformation pInfo){

        Dimension dimension = new Dimension(290, 70);
        SpecificDateFormat dateFormat = new SpecificDateFormat();

        title.setText("<html>" + pInfo.getTitle() + "<html>");
        title.setPreferredSize(dimension);
        description.setText("<html>" +pInfo.getDescription()+ "</html>");
        description.setPreferredSize(dimension);
        startDate.setText("Startar: " + dateFormat.getTimeAndDayFromDate(
                pInfo.getEpisodeDate_Start()));
        endDate.setText("Slutar: " + dateFormat.getTimeAndDayFromDate(
                pInfo.getEpisodeDate_End()));
    }

    /**
     * Sets up a return button, will take user back to channel tableau.
     * @param listener ActionListener
     */
    private void setupReturnButton(ActionListener listener){
        returnToTableau.addActionListener(listener);
        middle.add(returnToTableau, BorderLayout.CENTER);

    }

}
