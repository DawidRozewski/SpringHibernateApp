package pl.coderslab.SpringHibernateApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.SpringHibernateApp.entity.Author;
import pl.coderslab.SpringHibernateApp.repository.AuthorRepository;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/author")
public class AuthorController {

    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    @GetMapping("/save")
    @ResponseBody
    public String persist() {
        Author author = new Author();
        author.setFirstName("Elon");
        author.setLastName("Musk");
        authorRepository.save(author);
        return author.toString();
    }

    @GetMapping("/find/{id}")
    @ResponseBody
    public String findById(@PathVariable long id) {
        Author author = authorRepository.getById(id);
        return author.toString();
    }

    @GetMapping("/update/{id}/{firstName}")
    @ResponseBody
    public String merge(@PathVariable long id, @PathVariable String firstName) {
        Author author = authorRepository.getById(id);
        author.setFirstName(firstName);
        return author.toString();
    }

    @GetMapping("/remove/{id}")
    @ResponseBody
    public String remove(@PathVariable long id) {
        authorRepository.deleteById(id);
        return "Usunieto autora";
    }

    @GetMapping("/all")
    @ResponseBody
    public String findAll()  {
        List<Author> allAuthors = authorRepository.findAll();
        return allAuthors.stream()
                .map(Author::getFirstName)
                .collect(Collectors.joining("<br />"));
    }

}
