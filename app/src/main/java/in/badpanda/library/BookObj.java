package in.badpanda.library;

import android.graphics.Bitmap;

public class BookObj {
    String title ;
    Bitmap thumbnail ;

    public String getTitle() {
        return title;
    }

    public BookObj(String title, Bitmap thumbnail, String author) {
        this.title = title;
        this.thumbnail = thumbnail;
        this.author = author;
    }

    public Bitmap getThumbnail() {
        return thumbnail;
    }

    public String getAuthor() {
        return author;
    }

    String author;
}
