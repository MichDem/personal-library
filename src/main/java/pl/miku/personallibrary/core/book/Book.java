package pl.miku.personallibrary.core.book;

import org.hibernate.annotations.GenericGenerator;
import pl.miku.personallibrary.core.author.Author;
import pl.miku.personallibrary.core.category.Category;
import pl.miku.personallibrary.core.volume.Volume;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @Column(name = "id")
    @GenericGenerator(
            name = "book_id_seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "book_id_seq"),
                    @org.hibernate.annotations.Parameter(name= "INCREMENT", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MINVALUE", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MAXVALUE", value = "2147483647"),
                    @org.hibernate.annotations.Parameter(name = "CACHE", value = "1")
            }
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_id_seq")
    private long id;

    @Column(name = "title")
    private String title;

    @NotEmpty
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "volumes")
    @JoinTable(
            name = "volumes",
            joinColumns = {@JoinColumn(name = "book_id")}
    )
    private Set<Volume> volumes = new HashSet<>();

    @NotEmpty
    @ManyToMany(mappedBy = "books")
    private Set<Author> authors = new HashSet<>();

    @NotEmpty
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(name = "book_category",
            joinColumns = { @JoinColumn(name = "id_book") },
            inverseJoinColumns = { @JoinColumn(name = "id_category") }
    )
    private Set<Category> categories = new HashSet<>();

    public long getId() {
        return id;
    }

    public Book setId(long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Book setTitle(String title) {
        this.title = title;
        return this;
    }

    public Set<Volume> getVolumes() {
        return volumes;
    }

    public Book setVolumes(Set<Volume> volumes) {
        this.volumes = volumes;
        return this;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public Book setAuthors(Set<Author> authors) {
        this.authors = authors;
        return this;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public Book setCategories(Set<Category> categories) {
        this.categories = categories;
        return this;
    }
}
