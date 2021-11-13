package pl.coderslab.SpringHibernateApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.SpringHibernateApp.entity.Author;
import pl.coderslab.SpringHibernateApp.entity.Book;
import pl.coderslab.SpringHibernateApp.entity.Category;
import pl.coderslab.SpringHibernateApp.entity.Publisher;
import pl.coderslab.SpringHibernateApp.repository.AuthorRepository;
import pl.coderslab.SpringHibernateApp.repository.BookRepository;
import pl.coderslab.SpringHibernateApp.repository.CategoryRepository;
import pl.coderslab.SpringHibernateApp.repository.PublisherRepository;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/book")
public class BookController {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final PublisherRepository publisherRepository;
    private final AuthorRepository authorRepository;

    public BookController(BookRepository bookRepository, CategoryRepository categoryRepository, PublisherRepository publisherRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
        this.publisherRepository = publisherRepository;
        this.authorRepository = authorRepository;
    }


    @GetMapping("/ratingBetween/{first}/{second}")
    @ResponseBody
    public String getByRating(@PathVariable int first,
                              @PathVariable int second) {

        return bookRepository.methodToFindBookByRating(first, second).stream()
                .map(Book::toString)
                .collect(Collectors.joining("<br />"));
    }

    @GetMapping("/find}")
    @ResponseBody
    public String findByPublisher() {
        return bookRepository.findAllByPublisherNameIsLike("HELION").stream()
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
        publisherRepository.save(publisher);
        Author author = authorRepository.getById(2L);
        Author author1 = authorRepository.getById(3L);
        Book book = new Book();
        book.getAuthors().add(author);
        book.getAuthors().add(author1);
        book.setTitle("Thinking in Java");
        book.setRating(10);
        book.setDescription("Amazing book");
        book.setPublisher(publisher);
        bookRepository.save(book);
        return book.toString();

    }

    @GetMapping("/find/{id}")
    @ResponseBody
    public String findById(@PathVariable Long id) {
        return bookRepository.getById(id).toString();
    }

    @GetMapping("/update/{id}/{title}")
    @ResponseBody
    public String update(@PathVariable Long id, @PathVariable String title) {
        Book book = bookRepository.getById(id);
        book.setTitle(title);
        bookRepository.save(book);
        return book.toString();
    }

    @GetMapping("/remove/{id}}")
    @ResponseBody
    public String remove(@PathVariable Long id) {
        bookRepository.deleteById(id);
        return "Usunieto ksiazke";
    }
        @GetMapping("/all")
        @ResponseBody
        public String findAll()  {
         List<Book> allBooks = bookRepository.findAll();
        return allBooks.stream()
                .map(book -> book.getId() + ": " + book.getTitle())
                .collect(Collectors.joining("<br />"));
    }

    @GetMapping("/rating/{rating}")
    @ResponseBody
    public String findAllByRating(@PathVariable int rating) {
        List<Book> allBooksByRating = bookRepository.findAllByRating(rating);
        return allBooksByRating.stream()
                .map(book -> book.getId() +
                        ": " + book.getTitle() +
                        ": " + book.getRating())
                .collect(Collectors.joining("<br />"));
    }

//    @GetMapping("/publisher/any")
//    @ResponseBody
//    public String findWithAnyPublisher() {
//        List<Book> bookList = bookRepository.findWithAnyPublisher();
//        return bookList.stream()
//                .map(book -> book.getId() +
//                        ": " + book.getTitle() +
//                        " " + book.getPublisher())
//                .collect(Collectors.joining("<br />"));
//    }

    @GetMapping("/publisher/{publisherId}")
    @ResponseBody
    public String findByPublisher(@PathVariable long publisherId) {
        Publisher byId = publisherRepository.getById(publisherId);
        List<Book> bookList = bookRepository.findAllByPublisher(byId);
        return bookList.stream()
                .map(book -> book.getId() +
                        ": " + book.getTitle())
                .collect(Collectors.joining("<br />"));
    }
  @GetMapping("/author/{authorId}")
    @ResponseBody
    public String findBookByAuthor(@PathVariable long authorId) {
        Author author = authorRepository.getById(authorId);
        List<Book> bookList = bookRepository.findAllByAuthors(author);
        return bookList.stream()
                .map(book -> book.getId() +
                        ": " + book.getTitle())
                .collect(Collectors.joining("<br />"));
    }



}
