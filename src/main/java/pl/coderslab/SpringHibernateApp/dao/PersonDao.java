package pl.coderslab.SpringHibernateApp.dao;

import org.springframework.stereotype.Repository;
import pl.coderslab.SpringHibernateApp.entity.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class PersonDao {

    @PersistenceContext
    EntityManager entityManager;

    public void persist(Person person) {
        entityManager.persist(person);
    }

    public Person findById(long id) {
        return entityManager.find(Person.class,id);
    }

    public void merge(Person person) {
        entityManager.merge(person);
    }

    public void remove(long id) {
        Person person = findById(id);
        entityManager.remove(entityManager.contains(person) ? person : entityManager.merge(person));
    }



}
