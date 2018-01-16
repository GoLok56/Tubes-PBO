package io.github.golok56.models;

/**
 * @author Satria Adi Putra
 */
public class Pembelian {
 private int mId;
    private String mNama;
    private String mDate;
    private int mJumlah;
    private int mTotal;
    
    public Pembelian(int id, String nama, String date, int jumlah, int total){
        mId = id;
        mNama = nama;
        mDate = date;
        mJumlah = jumlah;
        mTotal = total;
    }
    
    public Pembelian(String nama, String date, int jumlah, int total){
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
