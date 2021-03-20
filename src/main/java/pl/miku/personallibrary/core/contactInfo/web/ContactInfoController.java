package pl.miku.personallibrary.core.contactInfo.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.miku.personallibrary.core.contactInfo.ContactInfoService;

import javax.validation.Valid;

@RestController
@RequestMapping("/contact_info")
public class ContactInfoController {
    private final ContactInfoService service;

    public ContactInfoController(ContactInfoService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ContactInfoView getContactInfo(@PathVariable Long id) {
        return service.getContactInfo(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ContactInfoView create(@RequestBody @Valid ContactInfoRequest request) {
        return service.create(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContactInfo(@PathVariable(name = "id") Long id) {
        service.delete(id);
    }

    @PutMapping("/{id}")
    public ContactInfoView updateContactInfo(
            @PathVariable(name = "id") Long id,
            @RequestBody @Valid ContactInfoRequest request
    ) {
        var author = service.findOrThrow(id);
        return service.update(author, request);
    }
}
