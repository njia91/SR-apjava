package GUI;

import SwedishRadioInfo.ProgramInformation;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import java.util.List;


/**
 * This class extends AbstractTableModel.
 * Overrides certain methods which makes it possible
 * for a JTable to display the info.
 *
 * @author Michael Andersson
 * @version
 */

public class ChannelLibrary extends AbstractTableModel{

    private List<ProgramInformation> pInfo;
    private int columnSize = 3;
    private SpecificDateFormat dateFormat;

    /**
     * Constructor for ChannelLibrary
     * @param pInfo List of ProgramInformation.
     */
    public ChannelLibrary(List<ProgramInformation> pInfo){
        this.pInfo = pInfo;
        dateFormat = new SpecificDateFormat();

    }

    /**
     * Gets the name of each column.
     * @param columnIndex int
     * @return String
     */
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

    /**
     * Returns how many rows the table should contain.
     * @return int
     */
    @Override
    public int getRowCount(){
        return pInfo.size();

    }

    /**
     * Returns how many column the table should contain.
     * @return int
     */
    @Override
    public int getColumnCount(){
        return columnSize;

    }

    /**
     * Gets the value at a certain position in the table
     * @param row int
     * @param column int
     * @return Object
     */
    @Override
    public Object getValueAt(int row, int column){

        ProgramInformation program = pInfo.get(row);

        switch( column ){
            case 0:
                return program.getTitle();
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

    /**
     * Returns type of class in a column.
     * @param column int
     * @return Class<?>
     */
    @Override
    public Class<?> getColumnClass(int column){

        switch( column ){
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            default:
                return null;

        }

    }
}

