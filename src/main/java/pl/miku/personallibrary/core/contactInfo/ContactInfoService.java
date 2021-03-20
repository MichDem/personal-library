package pl.miku.personallibrary.core.contactInfo;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.miku.personallibrary.core.contactInfo.converter.ContactInfoToContactInfoViewConverter;
import pl.miku.personallibrary.core.contactInfo.web.ContactInfoRequest;
import pl.miku.personallibrary.core.contactInfo.web.ContactInfoView;
import pl.miku.personallibrary.core.custommer.CustomerRepository;
import pl.miku.personallibrary.error.EntityNotFoundException;
import pl.miku.personallibrary.util.MessageUtil;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
public class ContactInfoService {
    private final ContactInfoRepository contactInfoRepository;
    private final MessageUtil messageUtil;
    private final ContactInfoToContactInfoViewConverter contactInfoToContactInfoViewConverter;
    private final CustomerRepository customerRepository;

    public ContactInfoService(ContactInfoRepository contactInfoRepository, MessageUtil messageUtil, ContactInfoToContactInfoViewConverter contactInfoToContactInfoViewConverter, CustomerRepository customerRepository) {
        this.contactInfoRepository = contactInfoRepository;
        this.messageUtil = messageUtil;
        this.contactInfoToContactInfoViewConverter = contactInfoToContactInfoViewConverter;
        this.customerRepository = customerRepository;
    }

    public ContactInfo findOrThrow(Long id) {
        return contactInfoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageUtil.getMessage("contactInfo.NotFound", id)));
    }

    public ContactInfoView getContactInfo(Long id) {
        var contactInfo = findOrThrow(id);
        return contactInfoToContactInfoViewConverter.convert(contactInfo);
    }

    public ContactInfoView create(ContactInfoRequest request) {
        var contactInfo = new ContactInfo();
        prepare(contactInfo, request);
        var save = contactInfoRepository.save(contactInfo);
        return contactInfoToContactInfoViewConverter.convert(save);
    }

    public Page<ContactInfoView> findAllContactInfo(Pageable pageable) {
        var contacts = contactInfoRepository.findAll(pageable);
        var views = new ArrayList<ContactInfoView>();
        contacts.forEach(contactInfo -> {
            var view = contactInfoToContactInfoViewConverter.convert(contactInfo);
            views.add(view);
        });
        return new PageImpl<>(views, pageable, contacts.getTotalElements());
    }

    @Transactional
    public void delete(Long id) {
        try {
            contactInfoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new EntityNotFoundException(messageUtil.getMessage("contactInfo.NotFound", id));
        }
    }

    public ContactInfoView update(ContactInfo contactInfo, ContactInfoRequest request) {
        var newContact = prepare(contactInfo, request);
        var saved = contactInfoRepository.save(newContact);
        return contactInfoToContactInfoViewConverter.convert(saved);
    }

    private ContactInfo prepare(ContactInfo contactInfo, ContactInfoRequest request) {
        contactInfo.setEmail(request.getEmail())
                .setPhone(request.getPhone());
        var customer = customerRepository.findById(request.getCustomerId().getId())
                .orElseThrow(() -> new EntityNotFoundException(messageUtil.getMessage("customer.NotFound", request.getCustomerId().getId())));
        return contactInfo
                .setCustomer(customer);
    }
}
