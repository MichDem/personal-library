package pl.miku.personallibrary.core.contactInfo.web;

public class ContactInfoView {
    private long id;
    private String email;
    private String phone;

    public long getId() {
        return id;
    }

    public ContactInfoView setId(long id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ContactInfoView setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public ContactInfoView setPhone(String phone) {
        this.phone = phone;
        return this;
    }
}
