package io.github.golok56.services;

import io.github.golok56.models.Admin;
import io.github.golok56.repositories.AdminRepository;
import java.sql.SQLException;

/**
 * @author Satria Adi Putra
 */
public class Login {
    private final AdminRepository mRepository = new AdminRepository();
    public Login(){}
    
    public boolean isValid(String username, String password) throws SQLException {
        Admin admin = mRepository.getAdmin(username, password);
        
        if(admin == null){
            return false;
        }
        
        Session.sAdmin = admin;
        return true;
    }
}
