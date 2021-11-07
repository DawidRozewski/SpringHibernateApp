package pl.coderslab.SpringHibernateApp.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.coderslab.SpringHibernateApp.entity.Author;
import pl.coderslab.SpringHibernateApp.repository.AuthorRepository;

@Component
public class AuthorConverter implements Converter<String, Author> {

    private final AuthorRepository authorRepository;

    public AuthorConverter(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author convert(String source) { //itemValue!
        return authorRepository.getById(Long.parseLong(source));
    }
}
