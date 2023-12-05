package com.example.framming;

public class EventDetailSortByArrayMovie implements java.util.Comparator<MovieFinal>{

    @Override
    public int compare(MovieFinal customerEvents1, MovieFinal customerEvents2) {
        Integer DateObject1 = customerEvents1.getOrdemArray();
        Integer DateObject2 = customerEvents2.getOrdemArray();


        if (DateObject2 > DateObject1)
            return -1;
        else if (DateObject1 == DateObject2)
            return DateObject1 - DateObject2;

        else return 1;
    }
}
