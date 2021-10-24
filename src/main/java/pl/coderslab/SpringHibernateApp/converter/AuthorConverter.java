package pl.coderslab.SpringHibernateApp.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.coderslab.SpringHibernateApp.dao.AuthorDao;
import pl.coderslab.SpringHibernateApp.entity.Author;

@Component
public class AuthorConverter implements Converter<String, Author> {
    private final AuthorDao authorDao;

    public AuthorConverter(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    public Author convert(String source) { //itemValue!
        return authorDao.findById(Integer.parseInt(source));
    }
}
