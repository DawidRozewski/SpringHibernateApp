package pl.coderslab.SpringHibernateApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.SpringHibernateApp.entity.Person;
import pl.coderslab.SpringHibernateApp.entity.PersonDetails;
import pl.coderslab.SpringHibernateApp.repository.PersonRepository;

@Controller
@RequestMapping("/person")
public class PersonController {

    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("person", new Person());
        return "/person/personForm";
    }

    @PostMapping("/form")
    @ResponseBody
    public String saveForm(@ModelAttribute("person") Person testPerson) {
        personRepository.save(testPerson);
        return "Zapisano osobe.";
    }


// METODY DO OBLSUGI PRZEZ @RequestParam
//    @GetMapping("/form")
//    public String showForm() {
//        return "/person/personForm";
//    }
//
//    @PostMapping("/form")
//    @ResponseBody
//    public String saveForm(@RequestParam String login,
//                           @RequestParam String password,
//                           @RequestParam String email) {
//        Person person = new Person();
//        person.setLogin(login);
//        person.setEmail(email);
//        person.setPassword(password);
//        personDao.persist(person);
//        return "Udalo sie zapisac osobe";
//
//    }

    @GetMapping("/save")
    @ResponseBody
    public String persist() {
        PersonDetails personDetails = new PersonDetails();
        personDetails.setFirstName("Jan");
        personDetails.setLastName("Kowalski");
        personDetails.setCity("Gniezno");
        personDetails.setStreetNumber(44);
        personDetails.setStreet("Gnieznieska");

        Person person = new Person();
        person.setPersonDetails(personDetails);
        person.setLogin("500kg");
        person.setPassword("tajne");
        person.setEmail("tajnyEmail@gmail.com");
        personRepository.save(person);
        return person.toString();

    }

    @GetMapping("/find/{id}")
    @ResponseBody
    public String findById(@PathVariable long id) {
        Person person = personRepository.getById(id);
        return person.toString();
    }

    @GetMapping("/update/{id}/{login}")
    @ResponseBody
    public String merge(@PathVariable long id, @PathVariable String login) {
        Person person = personRepository.getById(id);
        person.setLogin(login);
        return person.toString();
    }

    @GetMapping("/remove/{id}")
    @ResponseBody
    public String remove(@PathVariable long id) {
        personRepository.deleteById(id);
        return "Usunieto osobe";
    }

}
