/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fungsi;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Owner
 */
public class WarnaTable2 extends DefaultTableCellRenderer {

    public int kolom, kolom1, kolom2, kolom3, kolom4;

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (row % 2 == 1) {
            component.setBackground(new Color(229, 255, 241));
        } else {
            component.setBackground(new Color(255, 255, 255));
        }

        if (column == kolom) {
            component.setBackground(new Color(215, 215, 255));
            component.setForeground(new Color(255, 255, 255));
            if (!table.getValueAt(row, kolom).toString().equals("")) {
                component.setBackground(new Color(255, 255, 255));
                component.setForeground(new Color(55, 55, 175));
            }

        }
        if (column == kolom1) {
            component.setBackground(new Color(215, 215, 255));
            component.setForeground(new Color(255, 255, 255));
            if (!table.getValueAt(row, kolom1).toString().equals("")) {
                component.setBackground(new Color(255, 255, 255));
                component.setForeground(new Color(55, 55, 175));
            }
        }
        if (column == kolom2) {
            component.setBackground(new Color(215, 215, 255));
            component.setForeground(new Color(255, 255, 255));
            if (!table.getValueAt(row, kolom2).toString().equals("")) {
                component.setBackground(new Color(255, 255, 255));
                component.setForeground(new Color(55, 55, 175));
            }
        }
        if (column == kolom3) {
            component.setBackground(new Color(215, 215, 255));
            component.setForeground(new Color(255, 255, 255));
            if (!table.getValueAt(row, kolom3).toString().equals("")) {
                component.setBackground(new Color(255, 255, 255));
                component.setForeground(new Color(55, 55, 175));
            }
        }
        if (column == kolom4) {
            component.setBackground(new Color(215, 215, 255));
            component.setForeground(new Color(255, 255, 255));
            if (!table.getValueAt(row, kolom4).toString().equals("")) {
                component.setBackground(new Color(255, 255, 255));
                component.setForeground(new Color(55, 55, 175));
            }
        } else {
            component.setForeground(new Color(70, 70, 70));
        }
        return component;
    }

}
