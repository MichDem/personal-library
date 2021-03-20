package pl.miku.personallibrary.core.author.web;

import pl.miku.personallibrary.base.BaseRequest;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public final class AuthorRequest extends BaseRequest {
    @NotNull
    private String fullname;

    @NotEmpty
    private List<@Valid Id> books;

    public String getFullname() {
        return fullname;
    }

    public AuthorRequest setFullname(String fullname) {
        this.fullname = fullname;
        return this;
    }

    public List<Id> getBooks() {
        return books;
    }

    public AuthorRequest setBooks(List<Id> books) {
        this.books = books;
        return this;
    }
}
