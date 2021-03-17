package pl.miku.personallibrary.core.author.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.miku.personallibrary.core.author.Author;
import pl.miku.personallibrary.core.author.web.AuthorView;

@Component
public class AuthorToAuthorViewConverter implements Converter<Author, AuthorView> {
    @Override
    public AuthorView convert(Author author) {
        return new AuthorView()
                .setId(author.getId())
                .setFullName(author.getFullName());
    }
}
