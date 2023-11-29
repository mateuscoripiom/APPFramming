package com.example.framming;

public class EventDetailSortByArrayPop implements java.util.Comparator<ItemFinal>{

    @Override
    public int compare(ItemFinal customerEvents1, ItemFinal customerEvents2) {
        Integer DateObject1 = customerEvents1.getOrdemArray();
        Integer DateObject2 = customerEvents2.getOrdemArray();


        if (DateObject2 > DateObject1)
            return -1;
        else if (DateObject1 == DateObject2)
            return DateObject1 - DateObject2;

        else return 1;
    }
}
