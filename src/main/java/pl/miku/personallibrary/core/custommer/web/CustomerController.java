package pl.miku.personallibrary.core.custommer.web;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.miku.personallibrary.core.custommer.CustomerService;

import javax.validation.Valid;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public CustomerView getCustomer(@PathVariable Long id) {
        return service.getCustomer(id);
    }

    @GetMapping
    @ResponseBody
    public Page<CustomerView> getAllCustomers(
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC)Pageable pageable
    ) {
        return service.findAllCustomers(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public CustomerView create(@RequestBody @Valid CustomerRequest request) {
        return service.create(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable(name = "id") Long id) {
        service.delete(id);
    }

    @PutMapping("/{id}")
    public CustomerView updateCustomer(
            @PathVariable(name = "id") Long id,
            @RequestBody @Valid CustomerRequest request
    ) {
        var customer = service.findOrThrow(id);
        return service.update(customer, request);
    }
}
