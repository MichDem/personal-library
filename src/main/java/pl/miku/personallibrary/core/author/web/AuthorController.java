package pl.miku.personallibrary.core.author.web;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.miku.personallibrary.core.author.AuthorService;

import javax.validation.Valid;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService service;

    public AuthorController(AuthorService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public AuthorView getAuthor(@PathVariable Long id) {
        return service.getAuthor(id);
    }

    @GetMapping
    @ResponseBody
    public Page<AuthorView> getAllAuthors(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return service.findAllAuthors(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public AuthorView create(@RequestBody @Valid AuthorRequest request) {
        return service.create(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthor(@PathVariable(name = "id") Long id, @RequestBody @Valid AuthorRequest request) {
        service.delete(id);
    }

    @PutMapping("/{id}")
    public AuthorView updateAuthor(@PathVariable(name = "id") Long id, @RequestBody @Valid AuthorRequest request){
        var author = service.findOrThrow(id);
        return service.update(author, request);
    }
}
