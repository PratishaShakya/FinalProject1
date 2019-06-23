package com.example.finalproject;

public class Models {
    String title;

    String desc;
    int icon;

    //constructor

public Models(String title, String desc, int icon){
    this.title=title;
    this.desc=desc;
    this.icon=icon;
}
//getters
    public  String getTitle(){
    return this.title;
    }

    public  String getDesc(){
        return this.desc;
    }

    public  int getIcon(){
        return this.icon;
    }
}
