package GUI;

import SwedishRadioInfo.ProgramInformation;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @author Michael Andersson
 */
public class ProgramDescription extends JPanel{


    private JPanel uppPanel = new JPanel();
    private JPanel middle = new JPanel();
    private JPanel infoPanel = new JPanel();
    private JLabel picLabel;
    private JLabel description;
    private JLabel startDate;
    private JLabel endDate;
    private JLabel title;
    private SpecificDateFormat dateFormat;
    private JButton returnToTableau;


    public ProgramDescription(){
        super();
        this.setLayout(new BorderLayout());
        this.uppPanel = new JPanel();
        this.middle = new JPanel();
        this.infoPanel = new JPanel();
        this.setPreferredSize(new Dimension(600, 600));
        this.add(uppPanel, BorderLayout.NORTH);
        this.add(middle, BorderLayout.SOUTH);
        this.infoPanel = new JPanel();
        this.infoPanel.setLayout(new BoxLayout(infoPanel,
                BoxLayout.Y_AXIS));
        uppPanel.add(infoPanel, BorderLayout.EAST);
        this.picLabel = new JLabel();
        this.description = new JLabel();
        this.startDate = new JLabel();
        this.endDate = new JLabel();
        this.title = new JLabel();
        this.returnToTableau = new JButton("Gå tillbaka till kanaltablån.");
        returnToTableau.setPreferredSize(new Dimension(400,50));
        dateFormat = new SpecificDateFormat();
        Dimension space = new Dimension(0, 20);
        infoPanel.add(title);
        infoPanel.add(Box.createRigidArea(space));
        infoPanel.add(description);
        infoPanel.add(Box.createRigidArea(space));
        infoPanel.add(startDate);
        infoPanel.add(endDate);
        uppPanel.add(picLabel, BorderLayout.WEST);


    }



    public void setUpProgramInfo(ProgramInformation pInfo,
                                 ActionListener listener){

        setupProgramImage(pInfo.getImage());
        setupProgramDescription(pInfo);
        setupReturnButton(listener);
        this.revalidate();
    }

    private void setupProgramImage(URL imageURL){
        URLImageLoader loader = new URLImageLoader();
        BufferedImage img = loader.openURLImage(imageURL);



        picLabel.setSize(this.getPreferredSize());
        Image scaledImg = img.getScaledInstance(picLabel.getWidth()/3,
                picLabel.getHeight() / 3, Image.SCALE_SMOOTH);
        picLabel.setIcon(new ImageIcon(scaledImg));
        picLabel.setHorizontalAlignment(JLabel.CENTER);
        picLabel.setVerticalAlignment(JLabel.CENTER);
        uppPanel.add(picLabel, BorderLayout.WEST);
    }


    private void setupProgramDescription(ProgramInformation pInfo){

        Dimension dimension = new Dimension(290, 70);
        Dimension space = new Dimension(0, 20);
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


    private void setupReturnButton(ActionListener listener){
        returnToTableau.addActionListener(listener);
        middle.add(returnToTableau, BorderLayout.CENTER);

    }

}
