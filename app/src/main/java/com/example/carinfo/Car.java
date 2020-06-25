package com.example.carinfo;

public class Car {

    private String Title;
    private int Thumbnail;


    public Car(){

    }

    public Car(String title, int thumbnail){
        Title = title;
        Thumbnail = thumbnail;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        Thumbnail = thumbnail;
    }
}
