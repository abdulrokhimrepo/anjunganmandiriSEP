/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanzahmsanjungan;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.Color;
import javax.swing.UIManager;
import usu.widget.util.WidgetUtilities;

/**
 *
 * @author khanzasoft
 */
public class KhanzaHMSAnjungan {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        WidgetUtilities.invokeLater(() -> {
            HalamanUtamaDepan utama = HalamanUtamaDepan.getInstance();
            utama.setVisible(true);
        });
        try {
//            UIManager.setLookAndFeel(new FlatMacLightLaf());
//            UIManager.put("TabbedPane.showTabSeparators", true);
//            UIManager.put("TabbedPane.tabSeparatorsFullHeight", true);
//            UIManager.put("ScrollBar.showButtons", true);
//            UIManager.put("TabbedPane.selectedBackground", Color.LIGHT_GRAY);
//            UIManager.put("TabbedPane.underlineColor", Color.GREEN);
//            UIManager.put("TabbedPane.tabSeparatorColor", Color.darkGray);
//            UIManager.put("Component.arrowType", "chevron");
//            UIManager.put("Component.innerFocusWidth", 1);
//            UIManager.put("TextBoxGlass.innerFocusWidth", 1);
//            UIManager.put("TextBox.focusWidth", 1);
//            UIManager.put("Component.focusWidth", 1);
//            UIManager.put("Button.innerFocusWidth", 1);
//            UIManager.put("Button.arc", 0);
//            UIManager.put("Component.arc", 0);
//            UIManager.put("CheckBox.arc", 0);
//            UIManager.put("ProgressBar.arc", 0);
//            UIManager.put("TextBox.arc", 5);

            UIManager.setLookAndFeel(new FlatMacLightLaf());
            System.setProperty("flatlaf.animation", "true");
            UIManager.put("TabbedPane.showTabSeparators", true);
            UIManager.put("TabbedPane.tabSeparatorsFullHeight", true);
            UIManager.put("ScrollBar.showButtons", true);
            UIManager.put("ScrollBar.width", 15);
            UIManager.put("ScrollPane.smoothScrolling", true);
            UIManager.put("TabbedPane.selectedBackground", Color.LIGHT_GRAY);
            UIManager.put("TabbedPane.underlineColor", Color.GREEN);
            UIManager.put("TabbedPane.tabSeparatorColor", Color.darkGray);
            UIManager.put("Component.arrowType", "triangle");
            UIManager.put("Component.innerFocusWidth", 1);
            UIManager.put("TextBoxGlass.innerFocusWidth", 3);
            UIManager.put("TextBox.focusWidth", 3);
            UIManager.put("Component.focusWidth", 1);
            UIManager.put("Button.innerFocusWidth", 1);
            UIManager.put("Button.arc", 0);
            UIManager.put("Component.arc", 0);
            UIManager.put("CheckBox.arc", 0);
            UIManager.put("ProgressBar.arc", 0);
            UIManager.put("TextBox.arc", 5);
//            UIManager.put("TextField.caretBlinkRate", 40);
            UIManager.put("PasswordField.showCapsLock", true);
            UIManager.put("PasswordField.showRevealButton", true);
            UIManager.put("TextArea.selectionForeground", new Color(255, 255, 255));
            UIManager.put("TextArea.selectionBackground", new Color(38, 117, 191));
            UIManager.put("TextField.selectionForeground", new Color(255, 255, 255));
            UIManager.put("TextField.selectionBackground", new Color(38, 117, 191));
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
    }

}
