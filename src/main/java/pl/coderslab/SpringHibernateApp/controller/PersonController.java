package pl.coderslab.SpringHibernateApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.SpringHibernateApp.dao.PersonDao;
import pl.coderslab.SpringHibernateApp.entity.Person;

@Controller
@RequestMapping("/person")
public class PersonController {

    private final PersonDao personDao;

    public PersonController(PersonDao personDao) {
        this.personDao = personDao;
    }

    @GetMapping("/save")
    @ResponseBody
    public String persist() {
        Person person = new Person();
        person.setLogin("500kg");
        person.setPassword("tajne");
        person.setEmail("tajnyEmail@gmail.com");
        personDao.persist(person);
        return person.toString();

    }

    @GetMapping("/finf/{id}")
    @ResponseBody
    public String findById(@PathVariable long id) {
        Person person = personDao.findById(id);
        return person.toString();
    }

    @GetMapping("/update/{id}/{firstName}")
    @ResponseBody
    public String merge(@PathVariable long id, @PathVariable String firstName) {
        Person person = personDao.findById(id);
        person.setEmail("zmergowanyEmail@gmail.com");
        return person.toString();
    }

    @GetMapping("/remove/{id}")
    @ResponseBody
    public String remove(@PathVariable long id) {
        personDao.remove(id);
        return "Usunieto osobe";
    }


}
