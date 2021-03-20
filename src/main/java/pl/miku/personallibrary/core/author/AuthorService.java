package pl.miku.personallibrary.core.author;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.miku.personallibrary.base.BaseRequest;
import pl.miku.personallibrary.core.author.converter.AuthorToAuthorViewConverter;
import pl.miku.personallibrary.core.author.web.AuthorRequest;
import pl.miku.personallibrary.core.author.web.AuthorView;
import pl.miku.personallibrary.core.book.Book;
import pl.miku.personallibrary.core.book.BookRepository;
import pl.miku.personallibrary.error.EntityNotFoundException;
import pl.miku.personallibrary.util.MessageUtil;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final MessageUtil messageUtil;
    private final AuthorToAuthorViewConverter authorToAuthorViewConverter;

    public AuthorService(AuthorRepository authorRepository, BookRepository bookRepository, MessageUtil messageUtil, AuthorToAuthorViewConverter authorToAuthorViewConverter) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.messageUtil = messageUtil;
        this.authorToAuthorViewConverter = authorToAuthorViewConverter;
    }

    public Author findOrThrow(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageUtil.getMessage("author.NotFound", id)));
    }

    public AuthorView getAuthor(Long id) {
        var author = findOrThrow(id);
        return authorToAuthorViewConverter.convert(author);
    }

    public AuthorView create(AuthorRequest request) {
        var author = new Author();
        prepare(author, request);
        var save = authorRepository.save(author);
        return authorToAuthorViewConverter.convert(save);
    }

    public Page<AuthorView> findAllAuthors(Pageable pageable) {
        var authors = authorRepository.findAll(pageable);
        var views = new ArrayList<AuthorView>();
        authors.forEach(author -> {
            var view = authorToAuthorViewConverter.convert(author);
            views.add(view);
        });
        return new PageImpl<>(views, pageable, authors.getTotalElements());
    }

    @Transactional
    public void delete(Long id) {
        try {
            authorRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new EntityNotFoundException(messageUtil.getMessage("author.NotFound", id));
        }
    }

    public AuthorView update(Author author, AuthorRequest request) {
        var newAuthor = prepare(author, request);
        var authorToSave = authorRepository.save(newAuthor);
        return authorToAuthorViewConverter.convert(authorToSave);
    }

    private Author prepare(Author author, AuthorRequest request) {
        author.setFullName(request.getFullname());
        var bookList = bookRepository.findAllById(request.getBooks()
                .stream()
                .map(BaseRequest.Id::getId)
                .collect(Collectors.toSet()));
        var books = new HashSet<Book>(bookList);
        author.setBooks(books);
        return author;
    }
}
