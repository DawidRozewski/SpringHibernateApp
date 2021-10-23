package pl.coderslab.SpringHibernateApp.dao;

import org.springframework.stereotype.Repository;
import pl.coderslab.SpringHibernateApp.entity.Person;
import pl.coderslab.SpringHibernateApp.entity.PersonDetails;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class PersonDetailsDao {

    @PersistenceContext
    EntityManager entityManager;


    public void persist(PersonDetails person) {
        entityManager.persist(person);
    }

    public PersonDetails findById(long id) {
        return entityManager.find(PersonDetails.class,id);
    }

    public void merge(Person person) {
        entityManager.merge(person);
    }

    public void remove(long id) {
        PersonDetails personDetails = findById(id);
        entityManager.remove(entityManager.contains(personDetails) ? personDetails : entityManager.merge(personDetails));
    }



}
