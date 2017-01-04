package GUI;


import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 *
 * Adds a button on the first column.
 * @author Michael Andersson
 * @version 4 January 2017
 */
public class ProgramRenderer implements TableCellRenderer{

    /**
     * Will render the information on the table, store the
     *
     * @param table Jtable
     * @param value Object
     * @param isSelected isSelected
     * @param hasFocus hasFocus
     * @param row row
     * @param column column
     * @return Component
     */
    @Override
    public Component getTableCellRendererComponent(JTable table,
                                                   Object value,
                                                   boolean isSelected,
                                                   boolean hasFocus,
                                                   int row, int column){
        String program = (String)value;
        final JButton button = new JButton(program);
        button.setBorder(new BevelBorder(BevelBorder.RAISED));
        return button;
    }



}
