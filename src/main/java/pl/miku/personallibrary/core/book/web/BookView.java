package pl.miku.personallibrary.core.book.web;

import pl.miku.personallibrary.core.author.web.AuthorView;
import pl.miku.personallibrary.core.category.Category;
import pl.miku.personallibrary.core.category.web.CategoryView;
import pl.miku.personallibrary.core.volume.web.VolumeView;

import java.util.HashSet;
import java.util.Set;

public class BookView {
    private long id;
    private String title;
    private Set<VolumeView> volumes = new HashSet<>();
    private Set<AuthorView> authors = new HashSet<>();
    private Set<CategoryView> categories = new HashSet<>();

    public long getId() {
        return id;
    }

    public BookView setId(long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public BookView setTitle(String title) {
        this.title = title;
        return this;
    }

    public Set<VolumeView> getVolumes() {
        return volumes;
    }

    public BookView setVolumes(Set<VolumeView> volumes) {
        this.volumes = volumes;
        return this;
    }

    public Set<AuthorView> getAuthors() {
        return authors;
    }

    public BookView setAuthors(Set<AuthorView> authors) {
        this.authors = authors;
        return this;
    }

    public Set<CategoryView> getCategories() {
        return categories;
    }

    public BookView setCategories(Set<CategoryView> categories) {
        this.categories = categories;
        return this;
    }
}
