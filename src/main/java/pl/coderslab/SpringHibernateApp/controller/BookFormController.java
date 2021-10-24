package pl.coderslab.SpringHibernateApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.SpringHibernateApp.dao.AuthorDao;
import pl.coderslab.SpringHibernateApp.dao.BookDao;
import pl.coderslab.SpringHibernateApp.dao.PublisherDao;
import pl.coderslab.SpringHibernateApp.entity.Author;
import pl.coderslab.SpringHibernateApp.entity.Book;
import pl.coderslab.SpringHibernateApp.entity.Publisher;

import java.util.List;

@Controller
@RequestMapping("/book/form")
public class BookFormController {

    private final PublisherDao publisherDao;
    private final BookDao bookDao;
    private final AuthorDao authorDao;

    public BookFormController(PublisherDao publisherDao, BookDao bookDao, AuthorDao authorDao) {
        this.publisherDao = publisherDao;
        this.bookDao = bookDao;
        this.authorDao = authorDao;
    }

    @GetMapping("/show")
    public String showBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "/book/bookForm";
    }

    @PostMapping("/save")
    public String saveBook(@ModelAttribute("book") Book book) {
        bookDao.persist(book);
        return "redirect:/book/form/all";
    }
    @GetMapping("/all")
    public String showAllBooks(Model model) {
        model.addAttribute("books", bookDao.findAll());
        return "/book/bookListing";
    }

    @ModelAttribute("publishers")
    public List<Publisher> publishers() {
        return publisherDao.findAll();
    }

    @ModelAttribute("authors")
    public List<Author> authors() {
        return authorDao.findAll();
    }




}
