package pl.coderslab.SpringHibernateApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.SpringHibernateApp.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
