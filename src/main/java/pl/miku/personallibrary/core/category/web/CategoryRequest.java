package pl.miku.personallibrary.core.category.web;

import pl.miku.personallibrary.base.BaseRequest;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CategoryRequest extends BaseRequest {
    @NotNull
    private String name;

    public String getName() {
        return name;
    }

    public CategoryRequest setName(String name) {
        this.name = name;
        return this;
    }
}
