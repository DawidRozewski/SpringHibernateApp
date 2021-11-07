package pl.coderslab.SpringHibernateApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.SpringHibernateApp.entity.Author;
import pl.coderslab.SpringHibernateApp.entity.Book;
import pl.coderslab.SpringHibernateApp.entity.Publisher;
import pl.coderslab.SpringHibernateApp.repository.AuthorRepository;
import pl.coderslab.SpringHibernateApp.repository.BookRepository;
import pl.coderslab.SpringHibernateApp.repository.PublisherRepository;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/book/form")
public class BookFormController {
    private final PublisherRepository publisherRepository;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BookFormController(PublisherRepository publisherRepository, AuthorRepository authorRepository, BookRepository bookRepository) {
        this.publisherRepository = publisherRepository;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }


    @GetMapping("/all")
    public String showAllBooks(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "/book/bookListing";
    }

    @GetMapping("/add")
    public String showBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "/book/bookForm";
    }

    @PostMapping("/add")
    public String save(@ModelAttribute("book") @Valid Book book, BindingResult result) {
        if (result.hasErrors()) {
            return "/book/bookForm";
        }
        bookRepository.save(book);
        return "redirect:/book/form/all";
    }

    @GetMapping("/edit")
    public String prepareToEdit(@RequestParam long id, Model model) {
        model.addAttribute("book", bookRepository.getById(id));
        return "/book/bookForm";
    }

    @PostMapping("/edit")
    public String merge(@ModelAttribute("book") @Valid Book book, BindingResult result) {
        if (result.hasErrors()) {
            return "/book/bookForm";
        }
        bookRepository.save(book);
        return "redirect:/book/form/all";
    }

    @GetMapping("/remove")
    public String prepareToRemove(@RequestParam long id, Model model) {
        model.addAttribute("book", bookRepository.getById(id));
        return "/book/remove";
    }

    @PostMapping("/remove")
    public String remove(@RequestParam String confirmed, @RequestParam long id) {
        if ("yes".equals(confirmed)) {
            bookRepository.deleteById(id);
        }
        return "redirect:/book/form/all";
    }


    @ModelAttribute("publishers")
    public List<Publisher> publishers() {
        return publisherRepository.findAll();
    }

    @ModelAttribute("authors")
    public List<Author> authors() {
        return authorRepository.findAll();
    }


}
