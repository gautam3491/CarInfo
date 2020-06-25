package com.example.carinfo;

public class CarCategory {
    private String Title;
    private int Category;
    private int Type;
    private String Description;
    private int Thumbnail;
    private int ThumbnailLarge;
    private int Fav;

    public CarCategory(){
    }

    public CarCategory(String title, int category, int type, String description, int thumbnail, int thumbnailLarge, int fav){
        Title = title;
        Category = category;
        Type = type;
        Description = description;
        Thumbnail = thumbnail;
        ThumbnailLarge = thumbnailLarge;
        Fav = fav;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getCategory() {
        return Category;
    }

    public void setCategory(int category) {
        Category = category;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        Thumbnail = thumbnail;
    }

    public int getThumbnailLarge() {
        return ThumbnailLarge;
    }

    public void setThumbnailLarge(int thumbnailLarge) {
        ThumbnailLarge = thumbnailLarge;
    }

    public int getFav() {
        return Fav;
    }

    public void setFav(int fav) {
        Fav = fav;
    }
}
