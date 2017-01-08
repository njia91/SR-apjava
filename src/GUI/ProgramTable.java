package GUI;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

import java.util.Date;

/**
 * Extends JTable. This is the render episodes with specific
 * colors depending on air time.
 *
 * @author Michael Andersson
 * @version 4 January 2017
 */
public class ProgramTable extends JTable{

    private SpecificDateFormat dateFormat;

    /**
     * Constructor for ProgramTable
     */
    public ProgramTable(){
        super();
        dateFormat = new SpecificDateFormat();
        this.setRowHeight(30);

    }

    /**
     * Renders old programs red, current green and future white.
     * @param renderer TableCellRenderer
     * @param row int
     * @param column int
     * @return Component
     */
    @Override
    public Component prepareRenderer(TableCellRenderer renderer,
                                     int row, int column){

        Component c = super.prepareRenderer(renderer, row, column);

        int modelRow = convertRowIndexToModel(row);
        String start = (String)getModel().getValueAt(modelRow, 1);
        String end = (String)getModel().getValueAt(modelRow, 2);

        Date currentDate =  new Date();
        Date startDate = dateFormat.formatToDate(start);
        Date endDate = dateFormat.formatToDate(end);

        //Depending on air time color each row.
        if(currentDate.after(startDate) && currentDate.before(endDate)){
           c.setBackground(Color.GREEN);
        }else if (currentDate.before(startDate)){
            c.setBackground(Color.white);
        }else if (currentDate.after(endDate)){
            c.setBackground(Color.lightGray);
        }
        return c;
    }

}
