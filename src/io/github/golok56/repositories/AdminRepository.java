package io.github.golok56.repositories;

import com.sun.istack.internal.Nullable;
import io.github.golok56.models.Admin;
import io.github.golok56.services.Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Satria Adi Putra
 */
public class AdminRepository {
    private static final String TABLE_NAME = "admin";
    private static final String USERNAME_COLUMN = "username";
    private static final String PASSWORD_COLUMN = "password";

    @Nullable
    public Admin getAdmin(String username, String password) throws SQLException {
        String query = "SELECT " + USERNAME_COLUMN + ", " + PASSWORD_COLUMN + 
                " FROM " + TABLE_NAME + " WHERE " + USERNAME_COLUMN + "=?";
        
        PreparedStatement pStatement = Database.getConnection().prepareStatement(query);
        pStatement.setString(1, username);
        
        ResultSet rs = pStatement.executeQuery();
        if(rs.next()){
            if(password.equals(rs.getString(PASSWORD_COLUMN))){
                pStatement.close();
                return new Admin(username);
            }
        }
        
        pStatement.close();
        return null;
    }
}
