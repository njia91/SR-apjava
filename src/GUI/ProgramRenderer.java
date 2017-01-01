package GUI;

import Controller.EventController;
import SwedishRadioInfo.ProgramInformation;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @author Michael Andersson
 */
public class ProgramRenderer implements TableCellRenderer{


    private EventController eventCtrl;



    public ProgramRenderer(EventController eventCtrl){

        this.eventCtrl = eventCtrl;


    }


    @Override
    public Component getTableCellRendererComponent(JTable table,
                                                   Object value,
                                                   boolean isSelected,
                                                   boolean hasFocus,
                                                   int row, int column){
        JButton button = (JButton)value;
        if (isSelected) {
            button.setForeground(table.getSelectionForeground());
            button.setBackground(table.getSelectionBackground());
            System.out.println(button.getName());
            eventCtrl.showProgramInfo(button.getText());
        } else {
            button.setForeground(table.getForeground());
            button.setBackground(UIManager.getColor("Button.background"));
        }
        return button;
    }



}
