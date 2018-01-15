package io.github.golok56.models;

/**
 * @author Satria Adi Putra
 */
public class Obat {
    private String mKodeObat;
    private String mNamaObat;
    private String mSatuan;
    private String mKadaluarsa;
    private int mHarga;
    
    public Obat(String kode, String nama, String satuan, String kadaluarsa, int harga){
        mKodeObat = kode;
        mNamaObat = nama;
        mSatuan = satuan;
        mKadaluarsa = kadaluarsa;
        mHarga = harga;
    }
    
    public void setKode(String kode){
        mKodeObat = kode;
    }
    
    public void setNama(String nama){
        mNamaObat = nama;
    }
    
    public void setSatuan(String satuan){
        mSatuan = satuan;
    }
    
    public void setKadaluarsa(String kadaluarsa){
        mKadaluarsa = kadaluarsa;
    }
    
    public void setHarga(int harga){
        mHarga = harga;
    } 
    
    public String getKode(){
        return mKodeObat;
    }
    
    public String getNama(){
        return mNamaObat;
    }
    
    public String getSatuan(){
        return mSatuan;
    }
    
    public String getKadaluarsa(){
        return mKadaluarsa;
    }
    
    public int getHarga(){
        return mHarga;
    }
}
