package in.badpanda.library;

import android.graphics.Bitmap;

public class BookObj {
    String title ;
    Bitmap thumbnail ;
    String author;
    String description;



    public BookObj(String title, Bitmap thumbnail, String author, String description) {
        this.title = title;
        this.thumbnail = thumbnail;
        this.author = author;
        this.description = description;
    }

    public Bitmap getThumbnail() {
        return thumbnail;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }


}
