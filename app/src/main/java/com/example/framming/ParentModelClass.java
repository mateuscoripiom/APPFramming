package com.example.framming;

import java.util.List;

public class ParentModelClass {
    String title;
    List<String> childModelClasses;

    public ParentModelClass(String title, List<String> childModelClasses) {
        this.title = title;
        this.childModelClasses = childModelClasses;
    }
}
