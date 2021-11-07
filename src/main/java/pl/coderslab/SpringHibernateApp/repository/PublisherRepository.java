package pl.coderslab.SpringHibernateApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.SpringHibernateApp.entity.Publisher;

import java.util.List;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {

    List<Publisher> findByNip(String nip);
    List<Publisher> findByRegon(String regon);

}
