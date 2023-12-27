package org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static Date parseDateString(String dateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            return dateFormat.parse(dateString);
        } catch (ParseException e) {

            e.printStackTrace();
            return null;
        }
    }
}
