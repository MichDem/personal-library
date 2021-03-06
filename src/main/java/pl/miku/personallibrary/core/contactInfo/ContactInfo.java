package pl.miku.personallibrary.core.contactInfo;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import pl.miku.personallibrary.core.custommer.Customer;

import javax.persistence.*;

@Entity
@Table(name = "contact_info")
public class ContactInfo {

    @Id
    @Column(name = "id")
    @GenericGenerator(
            name = "contact_info_id_seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "contact_info_id_seq"),
                    @Parameter(name= "INCREMENT", value = "1"),
                    @Parameter(name = "MINVALUE", value = "1"),
                    @Parameter(name = "MAXVALUE", value = "2147483647"),
                    @Parameter(name = "CACHE", value = "1")
            }
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contact_info_id_seq")
    private long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    public long getId() {
        return id;
    }

    public ContactInfo setId(long id) {
        this.id = id;
        return this;
    }

    public Customer getCustomer() {
        return customer;
    }

    public ContactInfo setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ContactInfo setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public ContactInfo setPhone(String phone) {
        this.phone = phone;
        return this;
    }
}
