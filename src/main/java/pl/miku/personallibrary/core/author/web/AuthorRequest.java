package pl.miku.personallibrary.core.author.web;

import pl.miku.personallibrary.base.BaseRequest;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public final class AuthorRequest extends BaseRequest {
    @NotNull
    private String fullname;

    private long id;

    public String getFullname() {
        return fullname;
    }

    public AuthorRequest setFullname(String fullname) {
        this.fullname = fullname;
        return this;
    }

    public long getId() {
        return id;
    }

    public AuthorRequest setId(long id) {
        this.id = id;
        return this;
    }
}
