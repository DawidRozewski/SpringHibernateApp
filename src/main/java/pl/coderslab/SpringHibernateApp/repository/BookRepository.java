package pl.coderslab.SpringHibernateApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.SpringHibernateApp.entity.Author;
import pl.coderslab.SpringHibernateApp.entity.Book;
import pl.coderslab.SpringHibernateApp.entity.Category;
import pl.coderslab.SpringHibernateApp.entity.Publisher;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findFirstByTitle(String title);

    List<Book> findAllByCategory(Category category);

    List<Book> findAllByCategory_Id(long categoryId);

    List<Book> findAllByAuthors(Author author);

    List<Book> findAllByPublisher(Publisher publisher);

    List<Book> findAllByRating(int rating);

    List<Book> findAllByPublisherNameIsLike(String publisher);

    Book findTopByCategoryOrderByTitle(Category category);

}
