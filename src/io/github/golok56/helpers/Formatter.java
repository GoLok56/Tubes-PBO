package io.github.golok56.helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Satria Adi Putra
 */
public class Formatter {
    private static String DATABASE_FORMAT = "yyyy-MM-dd";
    
    public static String format(String dateString) throws ParseException{
        SimpleDateFormat sdfTo = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat sdfFrom = new SimpleDateFormat(DATABASE_FORMAT);
        
        Date date = sdfFrom.parse(dateString);
        
        return sdfTo.format(date);
    }
    
    public static String now(){
        SimpleDateFormat sdf = new SimpleDateFormat(DATABASE_FORMAT);
        return sdf.format(new Date());
    }
}
