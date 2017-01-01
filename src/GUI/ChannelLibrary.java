package GUI;

import SwedishRadioInfo.ChannelInformation;
import SwedishRadioInfo.ProgramInformation;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @author Michael Andersson
 */

public class ChannelLibrary extends AbstractTableModel{

    private List<ProgramInformation> pInfo;
    private int columnSize = 3;
    private SpecificDateFormat dateFormat;


    public ChannelLibrary(List<ProgramInformation> pInfo){
        this.pInfo = pInfo;
        dateFormat = new SpecificDateFormat();

    }


    @Override
    public String getColumnName(int columnIndex){
        switch( columnIndex ){
            case 0:
                return "Program";
            case 1:
                return "Starttid";
            case 2:
                return "Sluttid";
            default:
                return null;
        }
    }


    @Override
    public int getRowCount(){
        return pInfo.size();

    }


    @Override
    public int getColumnCount(){
        return columnSize;

    }


    @Override
    public Object getValueAt(int row, int column){

        ProgramInformation program = pInfo.get(row);

        switch( column ){
            case 0:
                final JButton b = new JButton(program.getTitle());
                b.setBorder(new BevelBorder(BevelBorder.RAISED));
                return b;
            case 1:
                return dateFormat.formatToString(
                        program.getEpisodeDate_Start());
            case 2:
                return dateFormat.formatToString(
                        program.getEpisodeDate_End());
            default:
                return null;
        }


    }

    @Override
    public Class<?> getColumnClass(int column){

        switch( column ){
            case 0:
                return JButton.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            default:
                return null;

        }

    }
}

