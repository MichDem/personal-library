package pl.miku.personallibrary.core.book.web;

import pl.miku.personallibrary.base.BaseRequest;

import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

public class BookRequest {
    @NotEmpty
    private String title;

    @NotEmpty
    private List<@Valid Id> volumes;

    @NotEmpty
    private List<@Valid Id> authors;

    @NotEmpty
    private List<@Valid Id> categories;

    public String getTitle() {
        return title;
    }

    public BookRequest setTitle(String title) {
        this.title = title;
        return this;
    }

    public List<Id> getVolumes() {
        return volumes;
    }

    public BookRequest setVolumes(List<Id> volumes) {
        this.volumes = volumes;
        return this;
    }

    public List<Id> getAuthors() {
        return authors;
    }

    public BookRequest setAuthors(List<Id> authors) {
        this.authors = authors;
        return this;
    }

    public List<Id> getCategories() {
        return categories;
    }

    public BookRequest setCategories(List<Id> categories) {
        this.categories = categories;
        return this;
    }
}
