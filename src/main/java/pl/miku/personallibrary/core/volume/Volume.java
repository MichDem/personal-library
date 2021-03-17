package pl.miku.personallibrary.core.volume;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import pl.miku.personallibrary.core.book.Book;

import javax.persistence.*;

@Entity
@Table(name = "volumes")
public class Volume {

    @Id
    @Column(name = "id")
    @GenericGenerator(
            name = "volume_id_seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "volume_id_seq"),
                    @Parameter(name= "INCREMENT", value = "1"),
                    @Parameter(name = "MINVALUE", value = "1"),
                    @Parameter(name = "MAXVALUE", value = "2147483647"),
                    @Parameter(name = "CACHE", value = "1")
            }
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "volume_id_seq")
    private long id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "volume_name")
    private String volumeName;

    @Column(name = "volume_number")
    private long volumeNumber;

    public long getId() {
        return id;
    }

    public Volume setId(long id) {
        this.id = id;
        return this;
    }

    public Book getBook() {
        return book;
    }

    public Volume setBook(Book book) {
        this.book = book;
        return this;
    }

    public String getIsbn() {
        return isbn;
    }

    public Volume setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public String getVolumeName() {
        return volumeName;
    }

    public Volume setVolumeName(String volumeName) {
        this.volumeName = volumeName;
        return this;
    }

    public long getVolumeNumber() {
        return volumeNumber;
    }

    public Volume setVolumeNumber(long volumeNumber) {
        this.volumeNumber = volumeNumber;
        return this;
    }
}
