package io.github.golok56.services;

import io.github.golok56.ui.DataObatFrame;
import io.github.golok56.ui.LoginFrame;
import io.github.golok56.ui.MainMenuFrame;
import io.github.golok56.ui.PemasokFrame;
import io.github.golok56.ui.PenjualanFrame;
import javax.swing.JFrame;

/**
 * @author Satria Adi Putra
 */
public class Navigation {
    public static void showMainMenu(JFrame frame){
        frame.dispose();
        new MainMenuFrame();
    }
    
    public static void showDataObat(JFrame frame){
        frame.dispose();
        new DataObatFrame();
    }
    
    public static void showLogin(JFrame frame){
        if(frame != null){
            frame.dispose();
        }
        
        new LoginFrame();
    }
    
    public static void showDataPemasok(JFrame frame){
        frame.dispose();
        new PemasokFrame();
    }
    
    public static void showDataPenjualan(JFrame frame){
        frame.dispose();
        new PenjualanFrame();
    }
}
