package pl.coderslab.SpringHibernateApp.dao;

import org.springframework.stereotype.Repository;
import pl.coderslab.SpringHibernateApp.entity.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;


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

    public List<Book> findAll() {
        Query query = entityManager.createQuery("SELECT b FROM Book b");
        return query.getResultList();
    }
   public List<Book> findAllByRating(int rating) {
        Query query = entityManager.createQuery("SELECT b FROM Book b WHERE b.rating = :givenRating");
        query.setParameter("givenRating", rating);
        return query.getResultList();
   }

   public List<Book> findBookWithPublisher() {
        Query query = entityManager.createQuery("SELECT b FROM Book b WHERE b.publisher IS NOT NULL");
       return query.getResultList();
   }
   public List<Book> findBookWithAuthor(String author) {
        Query query = entityManager.createQuery("SELECT b FROM Book b WHERE b.authors = :givenAuthor");
        query.setParameter("givenAuthor", author);
       return query.getResultList();
   }



}
