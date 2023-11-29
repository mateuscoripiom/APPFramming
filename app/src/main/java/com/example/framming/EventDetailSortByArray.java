package com.example.framming;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EventDetailSortByArray implements java.util.Comparator<ItemFavFinal>{

    @Override
    public int compare(ItemFavFinal customerEvents1, ItemFavFinal customerEvents2) {
        Integer DateObject1 = customerEvents1.getOrdemArray();
        Integer DateObject2 = customerEvents2.getOrdemArray();


        if (DateObject2 > DateObject1)
            return -1;
        else if (DateObject1 == DateObject2)
            return DateObject1 - DateObject2;

        else return 1;
    }
}
