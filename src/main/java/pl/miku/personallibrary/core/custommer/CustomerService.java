package pl.miku.personallibrary.core.custommer;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.miku.personallibrary.core.custommer.converter.CustomerToCustomerViewConverter;
import pl.miku.personallibrary.core.custommer.web.CustomerRequest;
import pl.miku.personallibrary.core.custommer.web.CustomerView;
import pl.miku.personallibrary.error.EntityNotFoundException;
import pl.miku.personallibrary.util.MessageUtil;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final MessageUtil messageUtil;
    private final CustomerToCustomerViewConverter customerToCustomerViewConverter;

    public CustomerService(CustomerRepository customerRepository, MessageUtil messageUtil, CustomerToCustomerViewConverter customerToCustomerViewConverter) {
        this.customerRepository = customerRepository;
        this.messageUtil = messageUtil;
        this.customerToCustomerViewConverter = customerToCustomerViewConverter;
    }

    public Customer findOrThrow(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageUtil.getMessage("customer.NotFound", id)));
    }

    public CustomerView getCustomer(Long id) {
        var customer = findOrThrow(id);
        return customerToCustomerViewConverter.convert(customer);
    }

    public CustomerView create(CustomerRequest request) {
        var customer = new Customer();
        prepare(customer, request);
        var save = customerRepository.save(customer);
        return customerToCustomerViewConverter.convert(save);
    }

    public Page<CustomerView> findAllCustomers(Pageable pageable) {
        var customers = customerRepository.findAll(pageable);
        var views = new ArrayList<CustomerView>();
        customers.forEach(customer -> {
            var view = customerToCustomerViewConverter.convert(customer);
            views.add(view);
        });
        return new PageImpl<>(views, pageable, customers.getTotalElements());
    }

    @Transactional
    public void delete(Long id) {
        try {
            customerRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new EntityNotFoundException(messageUtil.getMessage("customer.NotFound", id));
        }
    }

    public CustomerView update(Customer customer, CustomerRequest request) {
        var newCustomer = prepare(customer, request);
        var saved = customerRepository.save(newCustomer);
        return customerToCustomerViewConverter.convert(saved);
    }

    private Customer prepare(Customer customer, CustomerRequest request) {
        //TODO
        return customer;
    }
}
