package pl.miku.personallibrary.core.book.web;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.miku.personallibrary.core.book.BookService;

import javax.validation.Valid;

@RestController
@RequestMapping("/book")
public class BookController {
    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public BookView getBook(@PathVariable Long id) {
        return service.getBook(id);
    }

    @GetMapping
    @ResponseBody
    public Page<BookView> getAllBooks(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return service.findAllBooks(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public BookView create(@RequestBody @Valid BookRequest request) {
        return service.create(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable(name = "id") Long id) {
        service.delete(id);
    }

    @PutMapping("/{id}")
    public BookView updateBook(@PathVariable(name = "id") Long id, @RequestBody @Valid BookRequest request) {
        var book = service.findOrThrow(id);
        return service.update(book, request);
    }
}
