package pl.coderslab.SpringHibernateApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.SpringHibernateApp.dao.AuthorDao;
import pl.coderslab.SpringHibernateApp.dao.BookDao;
import pl.coderslab.SpringHibernateApp.dao.PublisherDao;
import pl.coderslab.SpringHibernateApp.entity.Author;
import pl.coderslab.SpringHibernateApp.entity.Book;
import pl.coderslab.SpringHibernateApp.entity.Publisher;

import javax.swing.*;
import javax.validation.Valid;
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

    @GetMapping("/all")
    public String showAllBooks(Model model) {
        model.addAttribute("books", bookDao.findAll());
        return "/book/bookListing";
    }

    @GetMapping("/add")
    public String showBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "/book/bookForm";
    }

    @PostMapping("/add")
    public String save(@ModelAttribute("book") @Valid Book book, BindingResult result) {
        if(result.hasErrors()) {
            return "/book/bookForm";
        }
        bookDao.persist(book);
        return "redirect:/book/form/all";
    }

//    @GetMapping("/edit/{id}")
//    public String prepareToEdit(@PathVariable long id, Model model) {
//        model.addAttribute("book", bookDao.findById(id));
//        return "/book/bookForm";
//    }
//
//    @PostMapping("/edit/{id}")
//    public String merge(@ModelAttribute("book") @PathVariable long id) {
//        bookDao.merge(bookDao.findById(id));
//        return "redirect:/book/form/all";
//    }
//
    @GetMapping("/edit")
    public String prepareToEdit(@RequestParam long id, Model model) {
        model.addAttribute("book", bookDao.findById(id));
        return "/book/bookForm";
    }

    @PostMapping("/edit")
    public String merge(@ModelAttribute("book") Book book) {
        bookDao.merge(book);
        return "redirect:/book/form/all";
    }

    @GetMapping("/remove")
    public String prepareToRemove(@RequestParam long id, Model model) {
        model.addAttribute("book", bookDao.findById(id));
        return "/book/remove";
    }
    @PostMapping("/remove")
    public String remove(@RequestParam String confirmed, @RequestParam long id) {
        if ("yes".equals(confirmed)) {
            bookDao.remove(id);
        }
        return "redirect:/book/form/all";
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
