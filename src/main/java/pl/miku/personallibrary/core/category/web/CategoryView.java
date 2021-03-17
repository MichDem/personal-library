package pl.miku.personallibrary.core.category.web;

import pl.miku.personallibrary.core.book.web.BookView;

import java.util.Set;

public class CategoryView {
    private long id;
    private String name;
    private Set<BookView> books;

    public long getId() {
        return id;
    }

    public CategoryView setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CategoryView setName(String name) {
        this.name = name;
        return this;
    }

    public Set<BookView> getBooks() {
        return books;
    }

    public CategoryView setBooks(Set<BookView> books) {
        this.books = books;
        return this;
    }
}
