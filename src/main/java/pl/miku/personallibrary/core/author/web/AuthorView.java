package pl.miku.personallibrary.core.author.web;

import pl.miku.personallibrary.core.book.web.BookView;

import java.util.HashSet;
import java.util.Set;

public class AuthorView {
    private long id;
    private String fullName;
    private Set<BookView> books = new HashSet<>();

    public long getId() {
        return id;
    }

    public AuthorView setId(long id) {
        this.id = id;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public AuthorView setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public Set<BookView> getBooks() {
        return books;
    }

    public AuthorView setBooks(Set<BookView> books) {
        this.books = books;
        return this;
    }
}
