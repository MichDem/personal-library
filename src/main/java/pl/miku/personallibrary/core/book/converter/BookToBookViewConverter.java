package pl.miku.personallibrary.core.book.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.miku.personallibrary.core.author.converter.AuthorToAuthorViewConverter;
import pl.miku.personallibrary.core.author.web.AuthorView;
import pl.miku.personallibrary.core.book.Book;
import pl.miku.personallibrary.core.book.web.BookView;
import pl.miku.personallibrary.core.category.converter.CategoryToCategoryViewConverter;
import pl.miku.personallibrary.core.category.web.CategoryView;
import pl.miku.personallibrary.core.volume.converter.VolumeToVolumeViewConverter;
import pl.miku.personallibrary.core.volume.web.VolumeView;

import java.util.HashSet;

@Component
public class BookToBookViewConverter implements Converter<Book, BookView> {
    private final VolumeToVolumeViewConverter volumeToVolumeViewConverter;
    private final AuthorToAuthorViewConverter authorToAuthorViewConverter;
    private final CategoryToCategoryViewConverter categoryToCategoryViewConverter;

    public BookToBookViewConverter(VolumeToVolumeViewConverter volumeToVolumeViewConverter, AuthorToAuthorViewConverter authorToAuthorViewConverter, CategoryToCategoryViewConverter categoryToCategoryViewConverter) {
        this.volumeToVolumeViewConverter = volumeToVolumeViewConverter;
        this.authorToAuthorViewConverter = authorToAuthorViewConverter;
        this.categoryToCategoryViewConverter = categoryToCategoryViewConverter;
    }

    @Override
    public BookView convert(Book book) {
        HashSet<VolumeView> volumes = convertBooks(book);
        HashSet<AuthorView> authors = convertAuthors(book);
        HashSet<CategoryView> categories = convertCategories(book);

        return new BookView()
                .setId(book.getId())
                .setTitle(book.getTitle())
                .setVolumes(volumes)
                .setAuthors(authors)
                .setCategories(categories);
    }

    private HashSet<CategoryView> convertCategories(Book book) {
        var categories = new HashSet<CategoryView>();
        book.getCategories().forEach(category -> {
            var categoryView = categoryToCategoryViewConverter.convert(category);
            categories.add(categoryView);
        });
        return categories;
    }

    private HashSet<AuthorView> convertAuthors(Book book) {
        var authors = new HashSet<AuthorView>();
        book.getAuthors().forEach(author -> {
            var authorView = authorToAuthorViewConverter.convert(author);
            authors.add(authorView);
        });
        return authors;
    }

    private HashSet<VolumeView> convertBooks(Book book) {
        var volumes = new HashSet<VolumeView>();
        book.getVolumes().forEach(volume -> {
            var volumeView = volumeToVolumeViewConverter.convert(volume);
            volumes.add(volumeView);
        });
        return volumes;
    }
}
