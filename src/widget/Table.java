package widget;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

/**
 *
 * @author usu
 */
public class Table extends JTable {

    private static final long serialVersionUID = 1L;

    public Table() {
        setForeground(new Color(0, 131, 62));
        setBackground(new Color(255, 255, 255));
        setGridColor(new Color(240, 200, 240));
        setFont(new java.awt.Font("Poppins", 0, 18));
        setRowHeight(50);
        setSelectionBackground(new Color(80, 80, 80));
        setSelectionForeground(new Color(130, 134, 0));
        getTableHeader().setBackground(new Color(240, 255, 255));
        getTableHeader().setBorder(new LineBorder(new Color(240, 255, 255)));
        getTableHeader().setFont(new java.awt.Font("Poppins", 0, 18));
        getTableHeader().setForeground(new Color(0, 131, 62));
    }
}
