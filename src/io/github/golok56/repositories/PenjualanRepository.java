package io.github.golok56.repositories;

import io.github.golok56.models.Obat;
import io.github.golok56.models.Penjualan;
import io.github.golok56.services.Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Satria Adi Putra
 */
public class PenjualanRepository {
    private static final String TABLE_NAME = "penjualan";
    private static final String _ID = "id";
    private static final String NAMA_COLUMN = "nama_obat";
    private static final String TANGGAL_COLUMN = "tanggal";
    private static final String JUMLAH_COLUMN = "jumlah";
    private static final String TOTAL_COLUMN = "total";
    
    public boolean insert(Penjualan penjualan) throws SQLException {
        String query = "INSERT INTO " + TABLE_NAME + " VALUES(DEFAULT, ?,?,?,?)";
        
        PreparedStatement pStatement = Database.getConnection().prepareStatement(query);
        pStatement.setString(1, penjualan.getNama());
        pStatement.setString(2, penjualan.getDate());
        pStatement.setInt(3, penjualan.getJumlah());
        pStatement.setInt(4, penjualan.getTotal());
        
        int row = pStatement.executeUpdate();
        pStatement.close();
        return row != 0;
    }
    
    public List<Penjualan> fetch() throws SQLException {
        ArrayList<Penjualan> penjualans = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;
        
        PreparedStatement pStatement = Database.getConnection().prepareStatement(query);
        
        ResultSet rs = pStatement.executeQuery();
        while(rs.next()){
            int id = rs.getInt(_ID);
            String nama = rs.getString(NAMA_COLUMN);
            String tanggal = rs.getString(TANGGAL_COLUMN);
            int jumlah = rs.getInt(JUMLAH_COLUMN);
            int total = rs.getInt(TOTAL_COLUMN);
            
            penjualans.add(new Penjualan(id, nama, tanggal, jumlah, total));
        }
        
        pStatement.close();
        return penjualans;
    }

    public boolean delete(int id) throws SQLException {
        String query = "DELETE FROM " + TABLE_NAME + " WHERE " + _ID + " = ?";
        
        PreparedStatement pStatement = Database.getConnection().prepareStatement(query);
        pStatement.setInt(1, id);
        
        int row = pStatement.executeUpdate();
        pStatement.close();
        return row != 0;
    }
}
