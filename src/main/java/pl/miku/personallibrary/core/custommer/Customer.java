package pl.miku.personallibrary.core.custommer;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import pl.miku.personallibrary.core.contactInfo.ContactInfo;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @Column(name = "id")
    @GenericGenerator(
            name = "customer_id_seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "customer_id_seq"),
                    @Parameter(name= "INCREMENT", value = "1"),
                    @Parameter(name = "MINVALUE", value = "1"),
                    @Parameter(name = "MAXVALUE", value = "2147483647"),
                    @Parameter(name = "CACHE", value = "1")
            }
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_id_seq")
    private long id;

    @Column(name = "full_name")
    private String fullName;

    @OneToMany(mappedBy = "customer")
    private Set<ContactInfo> contactInfo;

    public long getId() {
        return id;
    }

    public Customer setId(long id) {
        this.id = id;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public Customer setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public Set<ContactInfo> getContactInfo() {
        return contactInfo;
    }

    public Customer setContactInfo(Set<ContactInfo> contactInfo) {
        this.contactInfo = contactInfo;
        return this;
    }
}
