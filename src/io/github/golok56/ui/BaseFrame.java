package io.github.golok56.ui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * @author Satria Adi Putra
 */
public class BaseFrame extends JFrame {
    public void showDialog(Object msg){
        JOptionPane.showMessageDialog(this, msg);
    }
}
