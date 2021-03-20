package pl.miku.personallibrary.core.custommer.web;

import pl.miku.personallibrary.base.BaseRequest;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CustomerRequest extends BaseRequest {
    @NotNull
    private String fullName;

    public String getFullName() {
        return fullName;
    }

    public CustomerRequest setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }
}
