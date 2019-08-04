package com.example.mybrower;

public class MainContent {
    private String info;
    private int imageId;
    public MainContent(String info,int imageId)  {
        this.info=info;
        this.imageId=imageId;
    }
    public String  getName(){
        return info;
    }
    public int getImageId(){
        return imageId;
    }



}
