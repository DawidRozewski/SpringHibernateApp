package pl.coderslab.SpringHibernateApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.SpringHibernateApp.dao.AuthorDao;
import pl.coderslab.SpringHibernateApp.dao.BookDao;
import pl.coderslab.SpringHibernateApp.dao.PublisherDao;
import pl.coderslab.SpringHibernateApp.entity.Author;
import pl.coderslab.SpringHibernateApp.entity.Book;
import pl.coderslab.SpringHibernateApp.entity.Publisher;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/book")
public class BookController {

    private final BookDao bookDao;
    private final PublisherDao publisherDao;
    private final AuthorDao authorDao;
    public BookController(BookDao bookDao, PublisherDao publisherDao, AuthorDao authorDao) {
        this.bookDao = bookDao;
        this.publisherDao = publisherDao;
        this.authorDao = authorDao;
    }

    @GetMapping("/save")
    @ResponseBody
    public String save() {
        Publisher publisher = new Publisher();
        publisher.setName("HELION");
        publisherDao.persist(publisher);
        Author author = authorDao.findById(2);
        Author author1 = authorDao.findById(3);
        Book book = new Book();
        book.setAuthors(List.of(author, author1));
        book.setTitle("Thinking in Java");
        book.setRating(10);
        book.setDescription("Amazing book");
        book.setPublisher(publisher);
        bookDao.persist(book);
        return book.toString();

    }

    @GetMapping("/find/{id}")
    @ResponseBody
    public String findById(@PathVariable Long id) {
        return bookDao.findById(id).toString();
    }

    @GetMapping("/update/{id}/{title}")
    @ResponseBody
    public String update(@PathVariable Long id, @PathVariable String title) {
        Book book = bookDao.findById(id);
        book.setTitle(title);
        bookDao.merge(book);
        return book.toString();
    }

    @GetMapping("/remove/{id}}")
    @ResponseBody
    public String remove(@PathVariable Long id) {
        Book book = bookDao.findById(id);
        bookDao.remove(book);
        return "Usunieto ksiazke";
    }
        @GetMapping("/all")
        @ResponseBody
        public String findAll()  {
         List<Book> allBooks = bookDao.findAll();
        return allBooks.stream()
                .map(book -> book.getId() + ": " + book.getTitle())
                .collect(Collectors.joining("<br />"));
    }

    @GetMapping("/rating/{rating}")
    @ResponseBody
    public String findAllByRating(@PathVariable int rating) {
        List<Book> allBooksByRating = bookDao.findAllByRating(rating);
        return allBooksByRating.stream()
                .map(book -> book.getId() +
                        ": " + book.getTitle() +
                        ": " + book.getRating())
                .collect(Collectors.joining("<br />"));
    }

    @GetMapping("/book-publisher")
    @ResponseBody
    public String findBookByPublisher() {
        List<Book> bookList = bookDao.findBookWithPublisher();
        return bookList.stream()
                .map(book -> book.getId() +
                        ": " + book.getTitle() +
                        " " + book.getPublisher())
                .collect(Collectors.joining("<br />"));
    }

    @GetMapping("/byPublisher/{id}")
    @ResponseBody
    public String findBookByPublisher(@PathVariable long id) {
        List<Book> bookList = bookDao.findBookByPublisher(id);
        return bookList.stream()
                .map(book -> book.getId() +
                        ": " + book.getTitle() +
                        " " + book.getPublisher())
                .collect(Collectors.joining("<br />"));
    }



}
