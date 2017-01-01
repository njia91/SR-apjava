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


    JPanel uppPanel = new JPanel();
    JPanel middle = new JPanel();

    private JLabel picLabel;

    private JTextArea description;


    public ProgramDescription(){
        super();
        this.setLayout(new BorderLayout());

        this.setPreferredSize(new Dimension(600, 600));
        this.add(uppPanel, BorderLayout.NORTH);
        this.add(middle, BorderLayout.SOUTH);
        this.picLabel = new JLabel();
        this.description = new JTextArea();

    }



    public void setUpProgramInfo(ProgramInformation pInfo,
                                 ActionListener listener){

        setupProgramImage(pInfo.getImage());
        setupProgramDescription(pInfo);
        this.revalidate();
        uppPanel.revalidate();
    }

    private void setupProgramImage(URL imageURL){
        BufferedImage img = null;

        try {
            img = ImageIO.read( imageURL);
        } catch (IOException | IllegalArgumentException e){
            try {
                img = ImageIO.read(new File("images/Bild_saknas.svg.png"));
            } catch (IOException e1) {
                e1.printStackTrace();
                return;
            }
        }

        picLabel.setSize(this.getPreferredSize());
        Image scaledImg = img.getScaledInstance(picLabel.getWidth()/3,
                picLabel.getHeight() / 3, Image.SCALE_SMOOTH);
        picLabel.setIcon(new ImageIcon(scaledImg));
        picLabel.setHorizontalAlignment(JLabel.CENTER);
        picLabel.setVerticalAlignment(JLabel.CENTER);

        uppPanel.add(picLabel, BorderLayout.WEST);
        picLabel.revalidate();

    }


    private void setupProgramDescription(ProgramInformation pInfo){

        SpecificDateFormat dateFormat = new SpecificDateFormat();
        description.setPreferredSize(new Dimension(300,200));
        description.setCaretColor(this.getBackground());
        description.append(pInfo.getTitle());
        description.append("\n");
        description.append(pInfo.getDescription());
        description.append("\n");
        description.append("\n");
        description.append("Slutar: " +
                dateFormat.formatToString(pInfo.getEpisodeDate_End()));
        description.append("\n");
        description.append("Startar: " +
                dateFormat.formatToString(pInfo.getEpisodeDate_End()));

        description.setVisible(true);
        middle.add(description, BorderLayout.CENTER);
    }

}
