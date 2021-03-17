package pl.miku.personallibrary.core.custommer.web;

import pl.miku.personallibrary.core.contactInfo.ContactInfo;
import pl.miku.personallibrary.core.contactInfo.web.ContactInfoView;

import java.util.Set;

public class CustomerView {
    private long id;
    private String fullName;
    private Set<ContactInfoView> contactInfo;

    public long getId() {
        return id;
    }

    public CustomerView setId(long id) {
        this.id = id;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public CustomerView setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public Set<ContactInfoView> getContactInfo() {
        return contactInfo;
    }

    public CustomerView setContactInfo(Set<ContactInfoView> contactInfo) {
        this.contactInfo = contactInfo;
        return this;
    }
}
