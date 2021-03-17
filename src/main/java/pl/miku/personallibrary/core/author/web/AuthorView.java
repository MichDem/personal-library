package pl.miku.personallibrary.core.author.web;

public class AuthorView {
    private long id;
    private String fullName;

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
}
