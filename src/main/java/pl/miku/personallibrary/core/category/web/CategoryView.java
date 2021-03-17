package pl.miku.personallibrary.core.category.web;

public class CategoryView {
    private long id;
    private String name;

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
}
