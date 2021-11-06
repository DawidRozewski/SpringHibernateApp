package pl.coderslab.SpringHibernateApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.SpringHibernateApp.dao.AuthorDao;
import pl.coderslab.SpringHibernateApp.dao.BookDao;
import pl.coderslab.SpringHibernateApp.dao.PublisherDao;
import pl.coderslab.SpringHibernateApp.entity.Author;
import pl.coderslab.SpringHibernateApp.entity.Book;
import pl.coderslab.SpringHibernateApp.entity.Category;
import pl.coderslab.SpringHibernateApp.entity.Publisher;
import pl.coderslab.SpringHibernateApp.repository.BookRepository;
import pl.coderslab.SpringHibernateApp.repository.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/book")
public class BookController {

    private final PublisherDao publisherDao;
    private final AuthorDao authorDao;
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final BookDao bookDao;

    public BookController(BookDao bookDao, PublisherDao publisherDao, AuthorDao authorDao, BookRepository bookRepository, CategoryRepository categoryRepository, BookDao bookDao1) {
        this.publisherDao = publisherDao;
        this.authorDao = authorDao;
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
        this.bookDao = bookDao;
    }

//    @GetMapping("/test}")
//    @ResponseBody
//    public String find(@RequestParam String category,
//                       @RequestParam String title) {
//
//
//        return bookRepository.findFirstByCategoryAndTitleIs(category, title).toString();
//
//    }

    @GetMapping("/find}")
    @ResponseBody
    public String findByPublisher() {
        return bookRepository.findAllByPublisherNameIsLike("HELION").stream()
                .map(Book::toString)
                .collect(Collectors.joining("<br />"));

    }





    @GetMapping("/byAuthors/{authorId}")
    @ResponseBody
    public String findByAuthors(@PathVariable("authorId") int author) {
        Author author1 = authorDao.findById(author);
        return bookRepository.findAllByAuthors(author1).stream()
                .map(Book::toString)
                .collect(Collectors.joining("<br />"));
    }

    @GetMapping("/byPublisher/{publisherId}")
    @ResponseBody
    public String findByPublisher(@PathVariable("publisherId") int publisherId) {
        Publisher publisher = publisherDao.findById(publisherId);
        return bookRepository.findAllByPublisher(publisher).stream()
                .map(Book::toString)
                .collect(Collectors.joining("<br />"));
    }

    @GetMapping("/byRating/{rating}")
    @ResponseBody
    public String findByRating(@PathVariable("rating") int rating) {

        return bookRepository.findAllByRating(rating).stream()
                .map(Book::toString)
                .collect(Collectors.joining("<br />"));
    }






    @GetMapping("/byTitle/{title}")
    @ResponseBody
    public String findByTitleRepository(@PathVariable("title") String title) {
        return bookRepository.findFirstByTitle(title)
                .map(Book::toString)
                .orElse("Ksiazki nie znaleziono o zadanym tytule.");
    }

    @GetMapping("/byCat/{idCategory}")
    @ResponseBody
    public String findByCatObject(@PathVariable("idCategory") long idCategory) {
        Category cat = categoryRepository.getById(idCategory);
        return bookRepository.findAllByCategory(cat)
                .stream()
                .map(Book::toString)
                .collect(Collectors.joining("<br />"));
    }

    @GetMapping("/byCategoryId/{idCategory}")
    @ResponseBody
    public String findByCatId(@PathVariable("idCategory") long idCategory) {
        return bookRepository.findAllByCategory_Id(idCategory)
                .stream()
                .map(Book::toString)
                .collect(Collectors.joining("<br />"));
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
        bookDao.remove(id);
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

    @GetMapping("/publisher/any")
    @ResponseBody
    public String findWithAnyPublisher() {
        List<Book> bookList = bookDao.findBookWithPublisher();
        return bookList.stream()
                .map(book -> book.getId() +
                        ": " + book.getTitle() +
                        " " + book.getPublisher())
                .collect(Collectors.joining("<br />"));
    }

    @GetMapping("/publisher/{id}")
    @ResponseBody
    public String findBookByPublisher(@PathVariable long publisherId) {
        List<Book> bookList = bookDao.findAllByPublisher(publisherId);
        return bookList.stream()
                .map(book -> book.getId() +
                        ": " + book.getTitle())
                .collect(Collectors.joining("<br />"));
    }
  @GetMapping("/author/{id}")
    @ResponseBody
    public String findBookByAuthor(@PathVariable long authorId) {
        Author author = authorDao.findById(authorId);
        List<Book> bookList = bookDao.findAllByAuthor(author);
        return bookList.stream()
                .map(book -> book.getId() +
                        ": " + book.getTitle())
                .collect(Collectors.joining("<br />"));
    }



}
