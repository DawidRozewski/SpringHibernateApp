package pl.coderslab.SpringHibernateApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.coderslab.SpringHibernateApp.entity.Author;
import pl.coderslab.SpringHibernateApp.entity.Book;
import pl.coderslab.SpringHibernateApp.entity.Category;
import pl.coderslab.SpringHibernateApp.entity.Publisher;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("select b from Book b where b.title = :givenTitle")
    Optional<Book> methodToFindBookByTitle(@Param("givenTitle") String title);

    @Query("select b from Book b where b.category = :givenCategory")
    List<Book> methodToFindBookByCategoryObject(@Param("givenCategory") Category category);

    @Query("select b from Book b where b.rating between :firstRating and :secondRating")
    List<Book> methodToFindBookByRating(@Param("firstRating") int firstRating,
                                        @Param("secondRating") int secondRating);

    @Query("select b from Book  b where b.publisher = :publisher")
    List<Book> methodToFindByPublisher(@Param("publisher") String publisher);

    @Query(value = "select * from books where category_id = :givenCategory order by title limit 1", nativeQuery = true)
    Book getFirstBookFromCatergorySortingByTitle(@Param("givenCategory") Category category);

    Optional<Book> findFirstByTitle(String title);

    List<Book> findAllByCategory(Category category);

    List<Book> findAllByCategory_Id(long categoryId);

    List<Book> findAllByAuthors(Author author);

    List<Book> findAllByPublisher(Publisher publisher);

    List<Book> findAllByRating(int rating);

    List<Book> findAllByPublisherNameIsLike(String publisher);

    Book findTopByCategoryOrderByTitle(Category category);

}
