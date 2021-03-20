package pl.miku.personallibrary.core.book;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.miku.personallibrary.core.book.converter.BookToBookViewConverter;
import pl.miku.personallibrary.core.book.web.BookRequest;
import pl.miku.personallibrary.core.book.web.BookView;
import pl.miku.personallibrary.error.EntityNotFoundException;
import pl.miku.personallibrary.util.MessageUtil;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final MessageUtil messageUtil;
    private final BookToBookViewConverter bookToBookViewConverter;

    public BookService(BookRepository bookRepository, MessageUtil messageUtil, BookToBookViewConverter bookToBookViewConverter) {
        this.bookRepository = bookRepository;
        this.messageUtil = messageUtil;
        this.bookToBookViewConverter = bookToBookViewConverter;
    }

    public Book findOrThrow(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageUtil.getMessage("book.NotFound", id)));
    }

    public BookView getBook(Long id) {
        var book = findOrThrow(id);
        return bookToBookViewConverter.convert(book);
    }

    public BookView create(BookRequest request) {
        var book = new Book();
        prepare(book, request);
        var save = bookRepository.save(book);
        return bookToBookViewConverter.convert(save);
    }

    public Page<BookView> findAllBooks(Pageable pageable) {
        var books = bookRepository.findAll(pageable);
        var views = new ArrayList<BookView>();
        books.forEach(book -> {
            var view = bookToBookViewConverter.convert(book);
            views.add(view);
        });
        return new PageImpl<>(views, pageable, books.getTotalElements());
    }

    @Transactional
    public void delete(Long id) {
        try {
            bookRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new EntityNotFoundException(messageUtil.getMessage("book.NotFound", id));
        }
    }

    public BookView update(Book book, BookRequest request) {
        var newBook = prepare(book, request);
        var saved = bookRepository.save(newBook);
        return bookToBookViewConverter.convert(saved);
    }

    private Book prepare(Book book, BookRequest request) {
        //TODO
        return book;
    }
}
