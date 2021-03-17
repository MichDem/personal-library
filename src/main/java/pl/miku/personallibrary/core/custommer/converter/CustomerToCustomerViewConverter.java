package pl.miku.personallibrary.core.custommer.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.miku.personallibrary.core.contactInfo.converter.ContactInfoToContactInfoViewConverter;
import pl.miku.personallibrary.core.contactInfo.web.ContactInfoView;
import pl.miku.personallibrary.core.custommer.Customer;
import pl.miku.personallibrary.core.custommer.web.CustomerView;

import java.util.HashSet;

@Component
public class CustomerToCustomerViewConverter implements Converter<Customer, CustomerView> {
    private final ContactInfoToContactInfoViewConverter contactInfoToContactInfoViewConverter;

    public CustomerToCustomerViewConverter(ContactInfoToContactInfoViewConverter contactInfoToContactInfoViewConverter) {
        this.contactInfoToContactInfoViewConverter = contactInfoToContactInfoViewConverter;
    }

    @Override
    public CustomerView convert(Customer customer) {
        var contactInfo = new HashSet<ContactInfoView>();
        customer.getContactInfo().forEach(contact -> {
            var contactView = contactInfoToContactInfoViewConverter.convert(contact);
            contactInfo.add(contactView);
        });

        return new CustomerView()
                .setId(customer.getId())
                .setFullName(customer.getFullName())
                .setContactInfo(contactInfo);
    }
}
