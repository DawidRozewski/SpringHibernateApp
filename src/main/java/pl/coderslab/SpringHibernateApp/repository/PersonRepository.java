package pl.coderslab.SpringHibernateApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.SpringHibernateApp.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
