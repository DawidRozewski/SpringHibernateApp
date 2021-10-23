package pl.coderslab.SpringHibernateApp.dao;

import org.springframework.stereotype.Repository;
import pl.coderslab.SpringHibernateApp.entity.Publisher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class PublisherDao {

    @PersistenceContext
    EntityManager entityManager;

    public void persist(Publisher publisher) {
        entityManager.persist(publisher);
    }

    public Publisher findById(long id) {
        return entityManager.find(Publisher.class, id);
    }

    public Publisher merge(Publisher publisher) {
        return entityManager.merge(publisher);
    }

    public void remove(long id) {
        Publisher publisher = findById(id);
        entityManager.remove(entityManager.contains(publisher) ? publisher : entityManager.merge(publisher));
    }


}
