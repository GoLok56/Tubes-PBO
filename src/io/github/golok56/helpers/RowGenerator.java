package io.github.golok56.helpers;

import io.github.golok56.models.Obat;

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
}
