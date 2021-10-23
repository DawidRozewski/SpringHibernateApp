package pl.coderslab.SpringHibernateApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.SpringHibernateApp.dao.PersonDetailsDao;
import pl.coderslab.SpringHibernateApp.entity.PersonDetails;

@Controller
@RequestMapping("/person-details")
public class PersonDetailsController {

    private final PersonDetailsDao personDetailsDao;


    public PersonDetailsController(PersonDetailsDao personDetailsDao) {
        this.personDetailsDao = personDetailsDao;
    }

    @GetMapping("/save")
    @ResponseBody
    public String save() {
        PersonDetails personDetails = new PersonDetails();
        personDetails.setFirstName("Jan");
        personDetails.setLastName("Kowalski");
        personDetails.setStreetNumber(44);
        personDetails.setCity("Gniezno");
        personDetailsDao.persist(personDetails);
        return personDetails.toString();
    }
}
