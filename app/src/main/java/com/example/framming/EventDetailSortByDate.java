package com.example.framming;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EventDetailSortByDate implements java.util.Comparator<ItemFeedbackF>{

    @Override
    public int compare(ItemFeedbackF customerEvents1, ItemFeedbackF customerEvents2) {
        Date DateObject1 = StringToDate(customerEvents1.getDataCritica());
        Date DateObject2 = StringToDate(customerEvents2.getDataCritica());

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(DateObject1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(DateObject2);

        int month1 = cal1.get(Calendar.MONTH);
        int month2 = cal2.get(Calendar.MONTH);

        if (month1 > month2)
            return -1;
        else if (month1 == month2)
            return cal2.get(Calendar.DAY_OF_MONTH) - cal1.get(Calendar.DAY_OF_MONTH);

        else return 1;
    }

    public static Date StringToDate(String theDateString) {
        Date returnDate = new Date();
        if (theDateString.contains("-")) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            try {
                returnDate = dateFormat.parse(theDateString);
            } catch (ParseException e) {
                SimpleDateFormat altdateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    returnDate = altdateFormat.parse(theDateString);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            }
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            try {
                returnDate = dateFormat.parse(theDateString);
            } catch (ParseException e) {
                SimpleDateFormat altdateFormat = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    returnDate = altdateFormat.parse(theDateString);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return returnDate;
    }
}
