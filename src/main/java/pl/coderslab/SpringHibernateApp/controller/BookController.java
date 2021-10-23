package pl.coderslab.SpringHibernateApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String addBook(@RequestParam String title,
                          @RequestParam Integer rating,
                          @RequestParam String description) {
        Book book = new Book();
        book.setTitle(title);
        book.setRating(rating);
        book.setDescription(description);
        bookDao.saveBook(book);
        return "Id dodanej książki to: " + book.getId();

    }

    @GetMapping("/book/get/{id}")
    @ResponseBody
    public String getBook(@PathVariable Long id) {
        return bookDao.findId(id).toString();
    }

    @GetMapping("/book/update/{id}/{title}")
    @ResponseBody
    public String updateBook(@PathVariable Long id, @PathVariable String title) {
        Book book = bookDao.findId(id);
        book.setTitle(title);
        bookDao.update(book);
        return book.toString();
    }

    @GetMapping("/book/delete/{id}")
    @ResponseBody
    public String deleteBook(@PathVariable Long id) {
        Book book = bookDao.findId(id);
        bookDao.delete(book);
        return "deleted";
    }



}
