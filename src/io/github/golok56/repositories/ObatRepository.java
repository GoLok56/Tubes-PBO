package io.github.golok56.repositories;

import io.github.golok56.models.Obat;
import io.github.golok56.services.Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Satria Adi Putra
 */
public class ObatRepository {
    private static final String TABLE_NAME = "obat";
    private static final String KODE_COLUMN = "kode";
    private static final String NAMA_COLUMN = "nama";
    private static final String SATUAN_COLUMN = "satuan";
    private static final String KADALUARSA_COLUMN = "kadaluarsa";
    private static final String HARGA_COLUMN = "harga";
    
    public boolean insert(Obat obat) throws SQLException{
        String query = "INSERT INTO " + TABLE_NAME + " VALUES(?,?,?,?,?)";
        
        PreparedStatement pStatement = Database.getConnection().prepareStatement(query);
        pStatement.setString(1, obat.getKode());
        pStatement.setString(2, obat.getNama());
        pStatement.setString(3, obat.getSatuan());
        pStatement.setString(4, obat.getKadaluarsa());
        pStatement.setInt(5, obat.getHarga());
        
        int row = pStatement.executeUpdate();
        pStatement.close();
        return row != 0;
    }
    
    public List<Obat> fetch() throws SQLException {
        ArrayList<Obat> obats = new ArrayList<>();
        String query = "SELECT *, DATE_FORMAT(" + KADALUARSA_COLUMN + 
                ", '%d-%m-%Y') AS expired FROM " + TABLE_NAME;
        
        PreparedStatement pStatement = Database.getConnection().prepareStatement(query);
        
        ResultSet rs = pStatement.executeQuery();
        while(rs.next()){
            String kode = rs.getString(KODE_COLUMN);
            String nama = rs.getString(NAMA_COLUMN);
            String satuan = rs.getString(SATUAN_COLUMN);
            int harga = rs.getInt(HARGA_COLUMN);
            String kadaluarsa = rs.getString("expired");
            
            obats.add(new Obat(kode, nama, satuan, kadaluarsa, harga));
        }
        
        pStatement.close();
        return obats;
    }
    
    public boolean update(Obat obat) throws SQLException {
        String query = "UPDATE " + TABLE_NAME + " SET " + 
                NAMA_COLUMN + " = ?, " + SATUAN_COLUMN + " = ?, " +
                HARGA_COLUMN + " = ?, " + KADALUARSA_COLUMN + " = ? "
                + "WHERE " + KODE_COLUMN + " = ?";
        
        PreparedStatement pStatement = Database.getConnection().prepareCall(query);
        pStatement.setString(1, obat.getNama());
        pStatement.setString(2, obat.getSatuan());
        pStatement.setInt(3, obat.getHarga());
        pStatement.setString(4, obat.getKadaluarsa());
        pStatement.setString(5, obat.getKode());
        
        int row = pStatement.executeUpdate();
        pStatement.close();
        return row != 0;
    }
    
    public boolean delete(String kode) throws SQLException {
        String query = "DELETE FROM " + TABLE_NAME + " WHERE " + KODE_COLUMN +
                " = ?";
        
        PreparedStatement pStatement = Database.getConnection().prepareStatement(query);
        pStatement.setString(1, kode);
        
        int row = pStatement.executeUpdate();
        pStatement.close();
        return row != 0;
    }
}
