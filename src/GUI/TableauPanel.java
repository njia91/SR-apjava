package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.TableModel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

//import static com.sun.javafx.css.FontFace.FontFaceSrcType.URL;

/**
 * @author Michael Andersson
 */
public class TableauPanel extends JPanel {

    private JLabel picLabel;
    private JTable tableauTable;

    public TableauPanel(){
        super();
        this.setPreferredSize(new Dimension(600,600));
        this.setLayout(new BorderLayout());
        this.picLabel = new JLabel();
        tableauTable = new ProgramTable();
        tableauTable.setFillsViewportHeight(true);
        JScrollPane tableScroll = new JScrollPane(tableauTable);
        this.add(tableScroll, BorderLayout.CENTER);
    }



    public void setImage(URL imageURL){

        BufferedImage img= null;
        /* This is ugly, but if internet connection fails,
        *  try to load local image.*/
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
        Image scaledImg = img.getScaledInstance(picLabel.getWidth(),
                picLabel.getHeight() / 3, Image.SCALE_SMOOTH);
        picLabel.setIcon(new ImageIcon(scaledImg));
        picLabel.setHorizontalAlignment(JLabel.CENTER);
        picLabel.setVerticalAlignment(JLabel.CENTER);

        this.add(picLabel, BorderLayout.NORTH);
        picLabel.revalidate();
    }


    public void setTable(ChannelLibrary newProgramInfo, ProgramRenderer renderer){

        tableauTable.setModel(newProgramInfo);
        tableauTable.getColumn("Program").setCellRenderer(renderer);
        this.revalidate();
    }
}
