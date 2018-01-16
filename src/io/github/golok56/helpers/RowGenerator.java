package io.github.golok56.helpers;

import io.github.golok56.models.Obat;
import io.github.golok56.models.Penjualan;

/**
 * @author Satria Adi Putra
 */
public class RowGenerator {
    public static Object[] generate(Obat obat){
        Object[] row = { 
                obat.getKode(),
                obat.getNama(),
                obat.getSatuan(),
                String.valueOf(obat.getHarga()),
                obat.getKadaluarsa()
        };
        
        return row;
    }
    
    public static Object[] generate(Penjualan penjualan){
        Object[] row = {
            penjualan.getNama(),
            penjualan.getDate(),
            penjualan.getJumlah(),
            penjualan.getTotal()
        };
        return row;
    }
}
