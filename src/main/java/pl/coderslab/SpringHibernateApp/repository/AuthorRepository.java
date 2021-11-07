package pl.coderslab.SpringHibernateApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import pl.coderslab.SpringHibernateApp.entity.Author;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("select a from Author a where a.email like concat(:email, '%')")
    List<Author> allAuthorsLikeEmail(@PathVariable("email") String email);

    @Query("select a from Author a where a.pesel like concat(:pesel, '%')")
    List<Author> allAuthorsLikePesel(@PathVariable("pesel") String pesel);


    List<Author> findByEmail(String email);
    List<Author> findByPesel(String pesel);
    List<Author> findAllByLastName(String lastName);



}
