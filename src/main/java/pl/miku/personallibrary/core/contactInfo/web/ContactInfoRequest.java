package pl.miku.personallibrary.core.contactInfo.web;

import pl.miku.personallibrary.base.BaseRequest;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class ContactInfoRequest extends BaseRequest {
    @NotNull
    private String email;

    @NotNull
    private String phone;

    @Valid
    private Id customerId;

    public String getEmail() {
        return email;
    }

    public ContactInfoRequest setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public ContactInfoRequest setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public Id getCustomerId() {
        return customerId;
    }

    public ContactInfoRequest setCustomerId(Id customerId) {
        this.customerId = customerId;
        return this;
    }
}
