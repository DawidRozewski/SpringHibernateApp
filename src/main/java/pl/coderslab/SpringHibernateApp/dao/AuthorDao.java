package pl.coderslab.SpringHibernateApp.dao;

import org.springframework.stereotype.Repository;
import pl.coderslab.SpringHibernateApp.entity.Author;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class AuthorDao {

    @PersistenceContext
    EntityManager entityManager;

    public void persist(Author author) {
        entityManager.persist(author);
    }

    public Author findById(long id) {
        return entityManager.find(Author.class, id);
    }

    public void merge(Author author) {
        entityManager.merge(author);
    }

    public void remove(long id) {
        Author author = findById(id);
        entityManager.remove(entityManager.contains(author) ? author : entityManager.merge(author));

    }

}
