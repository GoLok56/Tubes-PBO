package io.github.golok56;

import io.github.golok56.services.Navigation;
import io.github.golok56.ui.LoginFrame;
import java.awt.EventQueue;

/**
 * @author Satria Adi Putra
 */
public class Main {
    public static void main(String[] args){ 
        EventQueue.invokeLater(() -> Navigation.showLogin(null));
    }
}
