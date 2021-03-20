package pl.miku.personallibrary.core.category.web;

import pl.miku.personallibrary.base.BaseRequest;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class CategoryRequest extends BaseRequest {
    @NotNull
    private String name;

    @NotEmpty
    private List<@Valid Id> books;

    public String getName() {
        return name;
    }

    public CategoryRequest setName(String name) {
        this.name = name;
        return this;
    }

    public List<Id> getBooks() {
        return books;
    }

    public CategoryRequest setBooks(List<Id> books) {
        this.books = books;
        return this;
    }
}
