package pl.coderslab.SpringHibernateApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.SpringHibernateApp.dao.AuthorDao;
import pl.coderslab.SpringHibernateApp.entity.Author;
import pl.coderslab.SpringHibernateApp.entity.Publisher;

import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/author")
public class AuthorController {

    private final AuthorDao authorDao;

    public AuthorController(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @GetMapping("/save")
    @ResponseBody
    public String persist() {
        Author author = new Author();
        author.setFirstName("Elon");
        author.setLastName("Musk");
        authorDao.persist(author);
        return author.toString();
    }

    @GetMapping("/find/{id}")
    @ResponseBody
    public String findById(@PathVariable long id) {
        Author author = authorDao.findById(id);
        return author.toString();
    }

    @GetMapping("/update/{id}/{firstName}")
    @ResponseBody
    public String merge(@PathVariable long id, @PathVariable String firstName) {
        Author author = authorDao.findById(id);
        author.setFirstName(firstName);
        return author.toString();
    }

    @GetMapping("/remove/{id}")
    @ResponseBody
    public String remove(@PathVariable long id) {
        authorDao.remove(id);
        return "Usunieto autora";
    }

    @GetMapping("/all")
    @ResponseBody
    public String findAll()  {
        List<Author> allAuthors = authorDao.findAll();
        return allAuthors.stream()
                .map(Author::getFirstName)
                .collect(Collectors.joining("<br />"));
    }

}
