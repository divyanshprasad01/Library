package in.badpanda.library;

public class BookObj {
    String title ;
    String thumbnail ;

    public String getTitle() {
        return title;
    }

    public BookObj(String title, String thumbnail, String author) {
        this.title = title;
        this.thumbnail = thumbnail;
        this.author = author;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getAuthor() {
        return author;
    }

    String author;
}
