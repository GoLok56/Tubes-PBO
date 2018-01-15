package io.github.golok56.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * @author Satria Adi Putra
 */
public class Database {
    
    private static final String LINK = "jdbc:mysql://localhost/apotik?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    
    private static Connection sConnection;
    
    private Database(){}
    
    public static Connection getConnection(){
        if(sConnection == null){
            try {
                Class.forName("com.mysql.jdbc.Driver");
                
                sConnection = DriverManager.getConnection(LINK, USER, PASSWORD);
            } catch(ClassNotFoundException | SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
            }
        }
        
        return sConnection;
    }
}
