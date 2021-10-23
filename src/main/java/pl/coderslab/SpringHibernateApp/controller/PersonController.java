package pl.coderslab.SpringHibernateApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.SpringHibernateApp.dao.PersonDao;
import pl.coderslab.SpringHibernateApp.dao.PersonDetailsDao;
import pl.coderslab.SpringHibernateApp.entity.Person;
import pl.coderslab.SpringHibernateApp.entity.PersonDetails;

@Controller
@RequestMapping("/person")
public class PersonController {

    private final PersonDao personDao;
    private final PersonDetailsDao personDetailsDao;

    public PersonController(PersonDao personDao, PersonDetailsDao personDetailsDao) {
        this.personDao = personDao;
        this.personDetailsDao = personDetailsDao;
    }

    @GetMapping("/save")
    @ResponseBody
    public String persist() {
        Person person = new Person();
        PersonDetails personDetails = personDetailsDao.findById(1);
        person.setPersonDetails(personDetails);
        person.setLogin("500kg");
        person.setPassword("tajne");
        person.setEmail("tajnyEmail@gmail.com");
        personDao.persist(person);
        return person.toString();

    }

    @GetMapping("/find/{id}")
    @ResponseBody
    public String findById(@PathVariable long id) {
        Person person = personDao.findById(id);
        return person.toString();
    }

    @GetMapping("/update/{id}/{login}")
    @ResponseBody
    public String merge(@PathVariable long id, @PathVariable String login) {
        Person person = personDao.findById(id);
        person.setLogin(login);
        return person.toString();
    }

    @GetMapping("/remove/{id}")
    @ResponseBody
    public String remove(@PathVariable long id) {
        personDao.remove(id);
        return "Usunieto osobe";
    }


}
