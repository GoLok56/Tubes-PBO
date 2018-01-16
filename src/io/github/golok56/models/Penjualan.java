package io.github.golok56.models;

/**
 * @author Satria Adi Putra
 */
public class Penjualan {
    private int mId;
    private String mNama;
    private String mDate;
    private int mJumlah;
    private int mTotal;
    
    public Penjualan(int id, String nama, String date, int jumlah, int total){
        mId = id;
        mNama = nama;
        mDate = date;
        mJumlah = jumlah;
        mTotal = total;
    }
    
    public Penjualan(String nama, String date, int jumlah, int total){
        mNama = nama;
        mDate = date;
        mJumlah = jumlah;
        mTotal = total;
    }
    
    public int getId(){
        return mId;
    }
    
    public String getNama(){
        return mNama;
    }
    
    public String getDate(){
        return mDate;
    }
    
    public int getJumlah(){
        return mJumlah;
    }
    
    public int getTotal(){
        return mTotal;
    }
    
    public void setDate(String date){
        mDate = date;
    }
}
