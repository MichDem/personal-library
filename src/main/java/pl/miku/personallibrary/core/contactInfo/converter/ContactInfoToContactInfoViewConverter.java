package pl.miku.personallibrary.core.contactInfo.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.miku.personallibrary.core.contactInfo.ContactInfo;
import pl.miku.personallibrary.core.contactInfo.web.ContactInfoView;

@Component
public class ContactInfoToContactInfoViewConverter implements Converter<ContactInfo, ContactInfoView> {
    @Override
    public ContactInfoView convert(ContactInfo contactInfo) {
        return new ContactInfoView()
                .setId(contactInfo.getId())
                .setEmail(contactInfo.getEmail())
                .setPhone(contactInfo.getPhone());
    }
}
