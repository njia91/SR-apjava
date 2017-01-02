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

        ProgramInformation program = (ProgramInformation)value;
        final JButton button = new JButton(program.getTitle());
        button.setBorder(new BevelBorder(BevelBorder.RAISED));
        if (isSelected) {
            table.clearSelection();
            eventCtrl.showProgramInfo(program);
        }

        return button;
    }



}
