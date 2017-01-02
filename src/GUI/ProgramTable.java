package GUI;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;

/**
 * @author Michael Andersson
 */
public class ProgramTable extends JTable{

    private SpecificDateFormat dateFormat;

    /**
     *
     */
    public ProgramTable(){
        super();
        dateFormat = new SpecificDateFormat();
        this.setRowHeight(40);

    }


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
