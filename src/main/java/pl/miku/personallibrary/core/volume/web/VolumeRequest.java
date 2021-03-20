package pl.miku.personallibrary.core.volume.web;

import pl.miku.personallibrary.base.BaseRequest;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

public class VolumeRequest extends BaseRequest {
    @NotEmpty
    private String volumeName;

    @NotEmpty
    private String isbn;

    private long volumeNumber;

    @Valid
    private long bookId;

    public String getVolumeName() {
        return volumeName;
    }

    public VolumeRequest setVolumeName(String volumeName) {
        this.volumeName = volumeName;
        return this;
    }

    public String getIsbn() {
        return isbn;
    }

    public VolumeRequest setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public long getVolumeNumber() {
        return volumeNumber;
    }

    public VolumeRequest setVolumeNumber(long volumeNumber) {
        this.volumeNumber = volumeNumber;
        return this;
    }

    public long getBookId() {
        return bookId;
    }

    public VolumeRequest setBookId(long bookId) {
        this.bookId = bookId;
        return this;
    }
}
