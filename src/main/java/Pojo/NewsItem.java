package Pojo;

import javafx.beans.property.*;

public class NewsItem {
    private final StringProperty date;
    private final StringProperty writer;
    private final StringProperty headline;
    private final IntegerProperty readCount;
    private final IntegerProperty likes;
    private final IntegerProperty comments;
    private final StringProperty url;

    public NewsItem(String date, String writer, String headline, int readCount, int likes, int comments, String url) {
        this.date = new SimpleStringProperty(date);
        this.writer = new SimpleStringProperty(writer);
        this.headline = new SimpleStringProperty(headline);
        this.readCount = new SimpleIntegerProperty(readCount);
        this.likes = new SimpleIntegerProperty(likes);
        this.comments = new SimpleIntegerProperty(comments);
        this.url = new SimpleStringProperty(url);
    }

    public String getDate() {
        return date.get();
    }

    public StringProperty dateProperty() {
        return date;
    }

    public String getWriter() {
        return writer.get();
    }

    public StringProperty writerProperty() {
        return writer;
    }

    public String getHeadline() {
        return headline.get();
    }

    public StringProperty headlineProperty() {
        return headline;
    }

    public int getReadCount() {
        return readCount.get();
    }

    public IntegerProperty readCountProperty() {
        return readCount;
    }

    public int getLikes() {
        return likes.get();
    }

    public IntegerProperty likesProperty() {
        return likes;
    }

    public int getComments() {
        return comments.get();
    }

    public IntegerProperty commentsProperty() {
        return comments;
    }

    public String getUrl() {
        return url.get();
    }

    public StringProperty urlProperty() {
        return url;
    }
}
