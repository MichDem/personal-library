package pl.miku.personallibrary.core.volume.web;

import pl.miku.personallibrary.core.book.web.BookView;

public class VolumeView {
    private long id;
    private BookView bookView;
    private String isbn;
    private String volumeName;
    private long volumeNumber;

    public long getId() {
        return id;
    }

    public VolumeView setId(long id) {
        this.id = id;
        return this;
    }

    public BookView getBookView() {
        return bookView;
    }

    public VolumeView setBookView(BookView bookView) {
        this.bookView = bookView;
        return this;
    }

    public String getIsbn() {
        return isbn;
    }

    public VolumeView setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public String getVolumeName() {
        return volumeName;
    }

    public VolumeView setVolumeName(String volumeName) {
        this.volumeName = volumeName;
        return this;
    }

    public long getVolumeNumber() {
        return volumeNumber;
    }

    public VolumeView setVolumeNumber(long volumeNumber) {
        this.volumeNumber = volumeNumber;
        return this;
    }
}
