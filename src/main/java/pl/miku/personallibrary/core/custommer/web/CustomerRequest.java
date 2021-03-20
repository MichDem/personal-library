package pl.miku.personallibrary.core.custommer.web;

import pl.miku.personallibrary.base.BaseRequest;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class CustomerRequest extends BaseRequest {
    @NotNull
    private String fullName;

    @NotEmpty
    private List<@Valid Id> contactId;

    public String getFullName() {
        return fullName;
    }

    public CustomerRequest setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public List<Id> getContactId() {
        return contactId;
    }

    public CustomerRequest setContactId(List<Id> contactId) {
        this.contactId = contactId;
        return this;
    }
}
