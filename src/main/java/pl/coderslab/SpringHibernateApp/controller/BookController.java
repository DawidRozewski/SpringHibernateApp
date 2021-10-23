package pl.coderslab.SpringHibernateApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.SpringHibernateApp.dao.BookDao;
import pl.coderslab.SpringHibernateApp.entity.Book;

@Controller
public class BookController {

    private final BookDao bookDao;

    public BookController(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @GetMapping("/book/add")
    @ResponseBody
    public String addBook(@PathVariable String title,
                          @PathVariable Integer rating,
                          @PathVariable String description) {
        Book book = new Book();
        book.setTitle(title);
        book.setRating(rating);
        book.setDescription(description);
        bookDao.saveBook(book);
        return "Id dodanej książki to: " + book.getId();

    }


}
