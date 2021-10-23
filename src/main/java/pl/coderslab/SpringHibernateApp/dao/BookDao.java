package pl.coderslab.SpringHibernateApp.dao;

import org.springframework.stereotype.Repository;
import pl.coderslab.SpringHibernateApp.entity.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;


@Repository
@Transactional
public class BookDao {

    @PersistenceContext
    EntityManager entityManager;

    public void persist(Book book) {
        entityManager.persist(book);
    }

    public Book findById(Long id) {
        return entityManager.find(Book.class, id);
    }

    public void merge(Book book) {
        entityManager.merge(book);
    }

    public void remove(Book book) {
        entityManager.remove(entityManager.contains(book) ? book : entityManager.merge(book));

    }
}
