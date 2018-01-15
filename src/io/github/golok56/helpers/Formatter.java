package io.github.golok56.helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Satria Adi Putra
 */
public class Formatter {
    public static String format(String dateString) throws ParseException{
        SimpleDateFormat sdfTo = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat sdfFrom = new SimpleDateFormat("yyyy-MM-dd");
        
        Date date = sdfFrom.parse(dateString);
        
        return sdfTo.format(date);
    }
}
