package GUI;


import Controller.EventController;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;



/**
 * Extends JPanel, shows the channel tableau in a JTable
 *
 * @author Michael Andersson
 * @version 4 January 2017
 */
public class TableauPanel extends JPanel {

    private JLabel picLabel;
    private JTable tableauTable;
    private EventController eCtrl;
    private ProgramRenderer renderer;

    public TableauPanel(Dimension d){
        super();
        this.setPreferredSize(d);
        this.setLayout(new BorderLayout());
        this.picLabel = new JLabel();
        tableauTable = new ProgramTable();
        tableauTable.setFillsViewportHeight(true);
        JScrollPane tableScroll = new JScrollPane(tableauTable);
        this.add(tableScroll, BorderLayout.CENTER);
        renderer = new ProgramRenderer();
    }


    /**
     * Gives an EventController to the Jtable. This is used to
     * know when and where the user pressed on the JTable
     * @param eCtrl EventController
     */
    public void setEventController(EventController eCtrl){
        this.eCtrl = eCtrl;
    }


    /**
     * Sets the image on the JPanel, given an URL.
     * @param imageURL URL
     */
    public void setImage(URL imageURL){
        URLImageLoader loader = new URLImageLoader();
        BufferedImage img =  loader.openURLImage(imageURL);


        Image scaledImg = img.getScaledInstance(this.getWidth()/3,
                this.getHeight() / 3, Image.SCALE_SMOOTH);
        picLabel.setIcon(new ImageIcon(scaledImg));
        picLabel.setHorizontalAlignment(JLabel.CENTER);
        picLabel.setVerticalAlignment(JLabel.CENTER);

        this.add(picLabel, BorderLayout.NORTH);
        picLabel.revalidate();
    }


    /**
     * Sets up the Jtable with the current information
     * @param newProgramInfo ChannelLibrary - the model.
     * @param renderer A renderer for the
     */
    public void setTable(ChannelLibrary newProgramInfo){

        tableauTable.setModel(newProgramInfo);
        tableauTable.getColumn("Program").setCellRenderer(renderer);
        ListSelectionModel l = tableauTable.getSelectionModel();
        l.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        l.addListSelectionListener(e -> {
            if(e.getValueIsAdjusting()){
                eCtrl.showProgramInfo(tableauTable.getSelectedRow());

            }
        });
        this.revalidate();
    }

}
