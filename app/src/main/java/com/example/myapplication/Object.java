package com.example.myapplication;

public class Object {
    private  String name;
    private int imageId;
    public Object(String name,int imageId){
        this.name=name;
        this.imageId=imageId;
    }
    public String getName(){
        return name;
    }
    public int getImageId(){
        return imageId;
    }
}
